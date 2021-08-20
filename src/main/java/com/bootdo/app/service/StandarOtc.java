package com.bootdo.app.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

@Service
public class StandarOtc {

  /**
   * 商户appid
   */
  static  String appid = "7UHoEzKSxRFySnRy";

  /**
   * 商户key
   */
  static String key = "23d0ad626dcd6a1e83ba230f82c02740";



  public JSONObject createUsdtOrder(String reallyName, String amount, String orderNum){

    JSONObject data = null;
    //请求rechargebyrmb接口
    String type = "2";
    String style = "1";
    String username = reallyName;
    String orderid = orderNum;
    String return_url = "http://localhost:90/";
    String notify_url = "http://www.yikamile.com:6868/api/standarOtcNotify";
    String address = "oiasjfofijaljooj";
    //对参数进行处理 开始
    // 对所有非空参数进行A-Z升序排序
    Map<String, String> params = new TreeMap<>();
    params.put("type", type);
    params.put("style", style);
    params.put("amount", amount);
    params.put("username", username);
    params.put("orderid", orderid);
    params.put("appid", appid);
    params.put("return_url", return_url);
    params.put("notify_url", notify_url);
    params.put("address", address);
    StringBuilder sb = new StringBuilder();
    for (String k : params.keySet()) {
      String value = params.get(k);
      if (null != value && value.length() > 0) {
        sb.append(k);
        sb.append(value);
      }
    }
    sb.append(key);

    String sign = "";
    try {
      sign = sha1(sb.toString());

      params.put("sign", sign);
    } catch (Exception e) {
      System.err.println("签名异常: " + e);
    }
    //todo 进行HTTP请求传递参数
    String result = "";
    try {
      result = httpPost("https://partner.standardotc.com/v1/trader/rechargebyrmb", params);
    } catch (Exception e) {
      System.err.println("请求结果异常: " + e);
    }
    if(result != null && result.length() > 0){

      JSONObject jsonObject1 = JSONObject.parseObject(result);
      JSONObject jsonObject = jsonObject1.getJSONObject("data");
      JSONObject cardInfo = new JSONObject();

      String receiver = jsonObject.getString("bank_name");


      String bankName = jsonObject.getString("bank");


      String bankBrandName = jsonObject.getString("branch");


      amount = jsonObject.getString("money");

      String cardId = jsonObject.getString("bank_card");

      cardInfo.put("bankAccName",receiver);
      cardInfo.put("cardId",cardId.replaceAll(" ", ""));
      cardInfo.put("bankName",bankName);
      cardInfo.put("bankBrandName",bankBrandName);
      data = new JSONObject();
      data.put("amountRmb", amount);
      data.put("oldPay",false);
      data.put("tradeStatus",true);
      data.put("cardInfo",cardInfo);
    }

    return data;
  }


  public static String sha1(String inStr) throws Exception {
    MessageDigest sha = null;
    try {
      sha = MessageDigest.getInstance("SHA");
    } catch (Exception e) {
      System.out.println(e.toString());
      e.printStackTrace();
      return "";
    }
    byte[] byteArray = inStr.getBytes("UTF-8");
    byte[] md5Bytes = sha.digest(byteArray);
    StringBuffer hexValue = new StringBuffer();
    for (int i = 0; i < md5Bytes.length; i++) {
      int val = ((int) md5Bytes[i]) & 0xff;
      if (val < 16) {
        hexValue.append("0");
      }
      hexValue.append(Integer.toHexString(val));
    }
    return hexValue.toString().toUpperCase();
  }
  public static String httpPost(String url, Map<String, String> params) {
    URL u = null;
    HttpURLConnection con = null;
    // 构建请求参数
    StringBuffer sb = new StringBuffer();
    if (params != null) {
      for (Map.Entry<String, String> e : params.entrySet()) {
        sb.append(e.getKey());
        sb.append("=");
        sb.append(e.getValue());
        sb.append("&");
      }
      sb.substring(0, sb.length() - 1);
    }
    System.out.println("send_url:" + url);
    System.out.println("send_data:" + sb.toString());
    // 尝试发送请求
    try {
      u = new URL(url);
      con = (HttpURLConnection) u.openConnection();
      //// POST 只能为大写，严格限制，post会不识别
      con.setRequestMethod("POST");
      con.setDoOutput(true);
      con.setDoInput(true);
      con.setUseCaches(false);
      con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
      osw.write(sb.toString());
      osw.flush();
      osw.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (con != null) {
        con.disconnect();
      }
    }
    // 读取返回内容
    StringBuffer buffer = new StringBuffer();
    try {
      //一定要有返回值，否则无法把请求发送给server端。
      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
      String temp;
      while ((temp = br.readLine()) != null) {
        buffer.append(temp);
        buffer.append("\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return buffer.toString();
  }


  // 将Unicode转为中文显示
  public static String decodeUnicode(final String dataStr) {
    int start = 0;
    int end = 0;
    int size = dataStr.length();
    final StringBuilder buffer = new StringBuilder();
    // 找到第一个\\u的位置
    while (start < size) {
      end = dataStr.indexOf("\\u", start);
      end = end < 0 ? size : end;
      String charStr;
      if (end == start) {
        // 如果start == end，则表示从start开始就是Unicode编码，
        start += 2;
        end += 6;
        charStr = dataStr.substring(start, end);
        // 16进制parse整形字符串。
        char letter = (char) Integer.parseInt(charStr, 16);
        buffer.append(letter);
      } else {
        // 如果start != end，则表示从start到end-1处都没有Unicode编码
        buffer.append(dataStr, start, end - 1);
      }
      // 重新调整start的位置
      start = end;
    }
    return buffer.toString();
  }

  public static void main(String[] args) {
    String temp = "{\"status\":1,\"data\":{\"money\":\"500\",\"num\":70.72135785,\"create_time\":1589964159,\"ltime\":\"10\",\"transact\":511,\"orderid\":\"20200520164221815623\",\"bank_card\":\"6325365214523652\",\"bank_name\":\"\\u4ea4\\u6613\\u5458\",\"bank\":\"\\u519c\\u4e1a\",\"branch\":\"\\u6df1\\u5733\",\"min\":3982373,\"second\":52}}";
    JSONObject jsonObject = JSONObject.parseObject(temp);
    System.out.println(jsonObject);
  }

}
