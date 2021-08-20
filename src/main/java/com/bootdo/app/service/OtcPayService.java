package com.bootdo.app.service;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.util.NumberUtil;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.TradeLogDO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OtcPayService {

  private String notifyUrl="http://www.yikamile.com:6868/api/otcNotify";
  private String callbackurl="http://localhost:8080";
  private String mchId="10322";
  private String withdrawNotifyUrl="http://localhost:8080/api/otcWithdrawNotify";

  private String mdkey="md3wepjngrhsj54fmvyhkglwacat64v7";
  private String gateWay="http://jvhe.bczone.com.cn/Pay_Index.html";
  private static Logger logger = LoggerFactory.getLogger(UsdtPayService.class);

  public JSONObject createUsdtOrder(String reallyName,String amount,String orderNum){
    Map<String, String> maper = new HashMap<>();

    maper.put("pay_memberid",mchId);
    maper.put("pay_orderid",orderNum);
    maper.put("pay_applydate",getTimeString(1).trim());
    maper.put("pay_bankcode","948");
    maper.put("pay_notifyurl",notifyUrl);
    maper.put("pay_callbackurl",callbackurl);
    maper.put("pay_amount",amount+".00");

    String signString = buildGetParams(maper,mdkey.trim());
    String sign = encrypt(signString);
    sign = sign.toUpperCase();

    maper.put("pay_md5sign",sign);
    maper.put("pay_productname","卡米乐");

    JSONObject data = null;



      try{
        Connection.Response response = Jsoup.connect(gateWay).ignoreContentType(true).method(Method.POST).data(maper).execute();


        URL url = response.url();
        //获取承兑人信息

        String tempUrl = url.toString();
        logger.info("请求支付的Url:"+tempUrl);

        Connection.Response payInfoRespone = Jsoup.connect(tempUrl).method(Method.GET).ignoreContentType(true).execute();
        Map<String,String> cookies = payInfoRespone.cookies();
        Document payInfos = payInfoRespone.parse();


        Elements attr = payInfos.select("meta[name=\"csrf-token\"]");
        Element tokenElement = attr.get(0);
        String token = tokenElement.attr("content");

        Elements payInfosElementsByClass = payInfos.getElementsByClass("btn_36QJF");


        String Cloud_Number = getParamByUrl(tempUrl,"Cloud_Number");
        String Merchant = getParamByUrl(tempUrl,"Mer");

        //提醒收款 http://www.bczone.com.cn/getstate
        Map<String,String> tixingParams = new HashMap<>();
        tixingParams.put("Cloud_Number",Cloud_Number);
        tixingParams.put("Merchant",Merchant);

        new Thread(){
          @Override
          public void run(){
            try{
              Thread.sleep(1000 * 30);
              String tixingResult = Jsoup.connect("http://www.bczone.com.cn/getstate")
                      .cookies(cookies)
                      .method(Method.POST)
                      .header("X-CSRF-TOKEN",token)
                      .ignoreContentType(true)
                      .data(tixingParams).execute().body();
              logger.info("tixingResult:"+tixingResult);
            }catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        JSONObject cardInfo = new JSONObject();

        String receiver = payInfosElementsByClass.get(0).attr("data-clipboard-text");
        String bankName = payInfosElementsByClass.get(1).attr("data-clipboard-text");
        String bankBrandName = payInfosElementsByClass.get(2).attr("data-clipboard-text");
        amount = payInfos.getElementsByClass("pay_text_3tGJu").get(0).getElementsByTag("span").html();

        String cardId = payInfosElementsByClass.get(3).attr("data-clipboard-text");

        cardInfo.put("bankAccName",receiver);
        cardInfo.put("cardId",cardId.replaceAll(" ", ""));
        cardInfo.put("bankName",bankName);
        cardInfo.put("bankBrandName",bankBrandName);
        data = new JSONObject();
        data.put("amountRmb", amount);

        data.put("oldPay",false);
        data.put("tradeStatus",true);
        data.put("cardInfo",cardInfo);
      }catch (Exception e){
        e.printStackTrace();
      }


    return data;
  }


  public String withdraw(MemberDO memberDO, TradeLogDO tradeLogDO){
    String url = "http://jvhe.bczone.com.cn/Payment_Dfpay_add.html";
    Map<String, String> maper = new HashMap<>();

    maper.put("mchid",mchId.trim());
    maper.put("out_trade_no",tradeLogDO.getTradeNo().trim());
    maper.put("bankname",memberDO.getBankName().trim());
    maper.put("subbranch",memberDO.getBankBranchName().trim());
    maper.put("accountname",memberDO.getReallyName().trim());
    maper.put("cardnumber",memberDO.getBankNo().trim());
    maper.put("money", NumberUtil.divide(tradeLogDO.getFactAmount(),"100").trim());
    String signString = buildGetParams(maper,mdkey.trim());
    String sign = encrypt(signString);
    sign = sign.toUpperCase();
    maper.put("sign",sign);

    try{
      Connection.Response response = Jsoup.connect(url).method(Method.POST).data(maper).ignoreContentType(true).execute();
      logger.info("response: "+response.parse().body());
    }catch (Exception e){
      e.printStackTrace();
    }

    return "";

  }


  /**
   * 当前时间
   * @return
   */
  public String getTimeString(int type)
  {
    Long timestamp = System.currentTimeMillis();
    String pattern = "";
    if(type == 1) {
      pattern = "yyyy-MM-dd HH:mm:ss";
    }else {
      pattern = "yyyyMMddHHmmss";
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    return simpleDateFormat.format(new Date(timestamp));
  }



  /**
   * ASSIC排序，拼接GET参数
   * @param maper
   * @return
   */
  public String buildGetParams(Map<String, String> maper,String mdkey) {
    List<Entry<String, String>> infoIds = new ArrayList<Entry<String, String>>(maper.entrySet());
    // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
    Collections.sort(infoIds, new Comparator<Entry<String, String>>() {

      public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
        return (o1.getKey()).toString().compareTo(o2.getKey());
      }
    });

    // 构造签名键值对的格式
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> item : infoIds) {
      if (item.getKey() != "respType") {
        String key = item.getKey();
        String val = item.getValue();
        if (!(val == "" || val == null)) {
          sb.append(key + "=" + val + "&");
        }
      }

    }

    sb.append("key=" + mdkey);
    String signString = sb.toString();
    return signString;
  }

  /**
   * MD5
   * @param dataStr
   * @return
   */
  public static String encrypt(String dataStr) {
    try {
      MessageDigest m = MessageDigest.getInstance("MD5");
      m.update(dataStr.getBytes("UTF8"));
      byte s[] = m.digest();
      String result = "";
      for (int i = 0; i < s.length; i++) {
        result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "";
  }

  /**
   * post请求(用于key-value格式的参数)
   * @param url
   * @param params
   * @return
   */
  public static String doPost(String url, Map params){
    BufferedReader in = null;
    HttpResponse response = null;
    try {
      // 定义HttpClient
      HttpClient client = new DefaultHttpClient();
      // 实例化HTTP方法
      HttpPost request = new HttpPost();
      request.setURI(new URI(url));

      //设置参数
      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
        String name = (String) iter.next();
        String value = String.valueOf(params.get(name));
        nvps.add(new BasicNameValuePair(name, value));

      }
      request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

      response = client.execute(request);
      int code = response.getStatusLine().getStatusCode();
      in = new BufferedReader(new InputStreamReader(response.getEntity()
              .getContent(),"utf-8"));
      StringBuffer sb = new StringBuffer("");
      String line = "";
      String NL = System.getProperty("line.separator");
      while ((line = in.readLine()) != null) {
        sb.append(line + NL);
      }

      in.close();

      return sb.toString();
    }
    catch(Exception e){
      logger.error("create order faild ",response);
      e.printStackTrace();
      return null;
    }
  }

  /**
   * post请求（用于请求json格式的参数）
   * @param url
   * @param params
   * @return
   */
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
}
