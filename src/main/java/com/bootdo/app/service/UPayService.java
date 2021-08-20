package com.bootdo.app.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.util.UPayConfig;
import com.bootdo.app.util.TradeUtil;
import com.bootdo.system.domain.MemberDO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UPayService {

  private static Logger logger = LoggerFactory.getLogger(UsdtPayService.class);

  public JSONObject createUsdtOrder(MemberDO memberDO,String amount,String orderNum){
    String orderNo = orderNum;
    String customerName = memberDO.getReallyName();
    String customerId = memberDO.getId()+"";
    //法币类型
    String accountType = "CNY";
    //充值金额 注意类型为BigDecimal
    BigDecimal accountIn = new BigDecimal(amount);
    //货币类型
    String currencyType = "usdt";
    //产品类型 APP 或者WEB
    String type = "WEB";
    Map<String,String> params = TradeUtil.params(orderNo, customerName,customerId, accountType, accountIn,currencyType, UPayConfig.return_url, UPayConfig.notify_url, UPayConfig.sign_type, UPayConfig.app_id,type);
    JSONObject data = null;

    try{
      Connection.Response response = Jsoup.connect(UPayConfig.gatewayUrl).method(Method.GET).ignoreContentType(true).data(params).execute();
      Map<String,String> headers = response.headers();

      logger.info("pre:"+headers);
      Connection.Response resultResponse = Jsoup.connect("http://pay.allbtpay.com/api/open/web/auth")
              .method(Method.POST)
              .ignoreContentType(true).data(params)
              .execute();

      URL url = resultResponse.url();

      String virtualId = getParamByUrl(url.toString(),"virtualId");
      String tempResult = Jsoup.connect(url.toString()).ignoreContentType(true).execute().body();
      logger.info("url="+url+"   tempResult:"+tempResult);
      JSONObject params1 = new JSONObject();
      params1.put("virtualId",virtualId);
      String result = doPost("https://webpay.allbtpay.com/api/open/auth/replace",params1.toJSONString());
      JSONObject jsonObject = JSONObject.parseObject(result);
      JSONObject dataVirtualId = jsonObject.getJSONObject("data");
      String id = dataVirtualId.getString("id");

      JSONObject checkParams = new JSONObject();
      checkParams.put("id",id);
      checkParams.put("inputName",memberDO.getReallyName());
      checkParams.put("virtualId",virtualId);
      //设置付款支付账号
      String checkIdNum = doPost("https://webpay.allbtpay.com/api/open/auth/checkIdNumber",checkParams.toJSONString());
      logger.info("checkIdNum: "+ checkIdNum);
      //matching
      JSONObject matchingParam = new JSONObject();
      matchingParam.put("id",id);
      String matchResult = doPost("https://webpay.allbtpay.com/api/open/auth/matching",matchingParam.toJSONString());
      String advertId =  "";
      if(matchResult.contains("code")){
        JSONObject matchJson = JSONObject.parseObject(matchResult);
        advertId = matchJson.getString("data");
      }
      //submit
      JSONObject submitParams = new JSONObject();
      submitParams.put("advertId",advertId);
      submitParams.put("id",id);
      submitParams.put("inputName",memberDO.getReallyName());
      String submitResult = doPost("https://webpay.allbtpay.com/api/open/auth/submit",submitParams.toJSONString());
      if(submitResult.contains("操作成功")){
        //获取承兑人的信息
        JSONObject bankInfoParam = new JSONObject();
        bankInfoParam.put("id",id);
        String bankInfoResult = doPost("https://webpay.allbtpay.com/api/open/auth/getDetails",bankInfoParam.toJSONString());
        if(bankInfoResult.contains("操作成功")){
          JSONObject bkJson = JSONObject.parseObject(bankInfoResult);
          String code = bkJson.getString("code");
          if(!code.equals("200")){
            return null;
          }
          data = new JSONObject();

          JSONObject payData = bkJson.getJSONObject("data");
          String amountFromOrder = payData.getString("account");
          JSONArray array = payData.getJSONArray("paymentList");
          JSONObject payJson = array.getJSONObject(0);

          JSONObject cardInfo = new JSONObject();

          String receiver = payJson.getString("paymentName");
          String bankName = payJson.getString("bankName");
          String cardNo = payJson.getString("bankNumber");
          String bankBrandName = payJson.getString("branchAddress");
          cardInfo.put("bankAccName",receiver);
          cardInfo.put("cardId",cardNo);
          cardInfo.put("bankName",bankName);
          cardInfo.put("bankBrandName",bankBrandName);
          data.put("amountRmb", amountFromOrder);

          data.put("oldPay",false);
          data.put("tradeStatus",true);
          data.put("cardInfo",cardInfo);

        }
      }

    }catch (Exception e){
      e.printStackTrace();
    }


    return data;
  }


  public static String getParamByUrl(String url, String name) {
    url += "&";
    String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";

    Pattern r = Pattern.compile(pattern);

    Matcher m = r.matcher(url);
    if (m.find( )) {
      System.out.println(m.group(0));
      return m.group(0).split("=")[1].replace("&", "");
    } else {
      return null;
    }
  }


  public static void main(String[] args) throws Exception{
    String virtualId = "705893097199894528";
    JSONObject params = new JSONObject();
    params.put("virtualId",virtualId);
  }

  public static String doPost(String url, String params) throws Exception {

    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);// 创建httpPost
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-Type", "application/json");
    String charSet = "UTF-8";
    StringEntity entity = new StringEntity(params, charSet);
    httpPost.setEntity(entity);
    CloseableHttpResponse response = null;

    try {

      response = httpclient.execute(httpPost);
      StatusLine status = response.getStatusLine();
      int state = status.getStatusCode();
      if (state == HttpStatus.SC_OK) {
        HttpEntity responseEntity = response.getEntity();
        String jsonString = EntityUtils.toString(responseEntity);
        return jsonString;
      }
      else{
        System.out.println("请求返回:"+state+"("+url+")");
      }
    }
    finally {
      if (response != null) {
        try {
          response.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      try {
        httpclient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
