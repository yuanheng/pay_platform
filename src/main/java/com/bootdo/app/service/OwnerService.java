package com.bootdo.app.service;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.model.PayInfo;
import com.bootdo.app.util.RedisUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OwnerService {

  @Autowired
  private PayInfoServer payInfoServer;

  public JSONObject createUsdtOrder(String reallyName, String amount, String orderNum) {
    PayInfo payInfo = payInfoServer.getPayInfo();


    JSONObject data = null;
    JSONObject cardInfo = new JSONObject();
    cardInfo.put("bankAccName", payInfo.getUsername());
    cardInfo.put("cardId", payInfo.getCardId());
    cardInfo.put("bankName", payInfo.getBankName());
    cardInfo.put("bankBrandName", payInfo.getBrankBranchName());
    data = new JSONObject();
    data.put("amountRmb", amount);
    data.put("oldPay", false);
    data.put("tradeStatus", true);
    data.put("cardInfo", cardInfo);

    return data;
  }


  public String withdraw(MemberDO memberDO, TradeLogDO tradeLogDO) {

    return "";

  }


  /**
   * 当前时间
   *
   * @return
   */
  public String getTimeString(int type) {
    Long timestamp = System.currentTimeMillis();
    String pattern = "";
    if (type == 1) {
      pattern = "yyyy-MM-dd HH:mm:ss";
    } else {
      pattern = "yyyyMMddHHmmss";
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    return simpleDateFormat.format(new Date(timestamp));
  }


  /**
   * ASSIC排序，拼接GET参数
   *
   * @param maper
   * @return
   */
  public String buildGetParams(Map<String, String> maper, String mdkey) {
    List<Entry<String, String>> infoIds = new ArrayList<Entry<String, String>>(maper.entrySet());
    // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
    Collections.sort(infoIds, new Comparator<Entry<String, String>>() {

      public int compare(Entry<String, String> o1, Entry<String, String> o2) {
        return (o1.getKey()).toString().compareTo(o2.getKey());
      }
    });

    // 构造签名键值对的格式
    StringBuilder sb = new StringBuilder();
    for (Entry<String, String> item : infoIds) {
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
   *
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
   *
   * @param url
   * @param params
   * @return
   */
  public static String doPost(String url, Map params) {
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
      for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
        String name = (String) iter.next();
        String value = String.valueOf(params.get(name));
        nvps.add(new BasicNameValuePair(name, value));

      }
      request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

      response = client.execute(request);
      int code = response.getStatusLine().getStatusCode();
      in = new BufferedReader(new InputStreamReader(response.getEntity()
              .getContent(), "utf-8"));
      StringBuffer sb = new StringBuffer("");
      String line = "";
      String NL = System.getProperty("line.separator");
      while ((line = in.readLine()) != null) {
        sb.append(line + NL);
      }

      in.close();

      return sb.toString();
    } catch (Exception e) {

      e.printStackTrace();
      return null;
    }
  }

  /**
   * post请求（用于请求json格式的参数）
   *
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
      } else {
        System.out.println("请求返回:" + state + "(" + url + ")");
      }
    } finally {
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
    if (m.find()) {
      System.out.println(m.group(0));
      return m.group(0).split("=")[1].replace("&", "");
    } else {
      return null;
    }
  }
}
