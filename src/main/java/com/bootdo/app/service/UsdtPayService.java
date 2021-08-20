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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class UsdtPayService {

	@Value("${ustd.gateway}")
	private String gateWay;
	@Value("${ustd.mchId}")
	private String mchId;
	@Value("${ustd.mdkey}")
	private String mdkey;
	@Value("${ustd.notifyUrl}")
	private String notifyUrl;
	@Value("${ustd.withdrawNotifyUrl}")
	private String withdrawNotifyUrl;

	private static Logger logger = LoggerFactory.getLogger(UsdtPayService.class);

	public JSONObject createUsdtOrder(String reallyName,String amount,String orderNum){
		Map<String, String> maper = new HashMap<>();
		String postPathString = "placeOrders";
		maper.put("order_currency","CNY");
		maper.put("platform_id","2");
		maper.put("order_amount",amount.trim());
		maper.put("notify_url",notifyUrl.trim());
		maper.put("back_url",notifyUrl.trim());
		maper.put("respType","2");
		maper.put("order_time",getTimeString(1).trim());
		maper.put("mch_order",orderNum.trim());
		maper.put("unique_mch",mchId.trim());

		String signString = buildGetParams(maper,mdkey.trim());
		String sign = encrypt(signString);
		sign = sign.toUpperCase();
		maper.put("sign",sign);
		maper.put("nick_name",reallyName.trim());
		// post 请求
		String resultString = doPost(gateWay+postPathString,maper);
		logger.info("pay order url:"+resultString);
		JSONObject data = new JSONObject();
		if(resultString.contains("success")){
			JSONObject temp = JSONObject.parseObject(resultString) ;
			String payUrl = temp.getString("url");
			try{

				JSONObject cardInfo = new JSONObject();
				Document document = Jsoup.connect(payUrl).ignoreHttpErrors(true).execute().parse();
				String receiver = document.getElementById("receiver").html();
				String bankName = document.getElementById("bank_name").html();
				String account = document.getElementById("account").html();
				String bankBrandName = document.getElementById("bank_brand_name").html();
				cardInfo.put("bankAccName",receiver);
				cardInfo.put("cardId",account);
				cardInfo.put("bankName",bankName);
				cardInfo.put("bankBrandName",bankBrandName);
				data.put("amountRmb", amount);

				data.put("oldPay",false);
				data.put("tradeStatus",true);
				data.put("cardInfo",cardInfo);
			}catch (Exception e){
				e.printStackTrace();
			}

		}else {
			logger.error("creator order faild "+resultString);
		}

		return data;
	}


	public String withdraw(MemberDO memberDO, TradeLogDO tradeLogDO){
		Map<String, String> maper = new HashMap<>();
		String postPathString = "withdraw";
		maper.put("unique_mch",mchId.trim());
		maper.put("mch_order",tradeLogDO.getTradeNo().trim());
		maper.put("order_amount",NumberUtil.divide(tradeLogDO.getFactAmount(),"100").trim());
		maper.put("notify_url",withdrawNotifyUrl.trim());
		maper.put("mch_order",tradeLogDO.getTradeNo().trim());
		maper.put("receiver",memberDO.getReallyName().trim());
		maper.put("account",memberDO.getBankNo().trim());
		maper.put("bank_name",memberDO.getBankName().trim());
		maper.put("bank_brand_name",memberDO.getBankBranchName().trim());

		String signString = buildGetParams(maper,mdkey.trim());
		String sign = encrypt(signString);
		sign = sign.toUpperCase();
		maper.put("sign",sign);
		return doPost(gateWay+postPathString,maper);

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
		List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(maper.entrySet());
		// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
		Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

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

				//System.out.println(name +"-"+value);
			}
			request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if(code == 200){	//请求成功
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
			else{	//
				System.out.println("状态码：" + code);
				return null;
			}
		}
		catch(Exception e){
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
}
