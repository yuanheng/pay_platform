package com.bootdo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest2 {
	public static void main(String[] args) {
		Transfer2 t = new Transfer2();
		t.read();
	}
}

class Transfer2 {
	private Map<String,String> numMap;
	private Map<String,Integer> priceMap;
	public Transfer2() {
		this.numMap = new HashMap<String,String>();
		this.priceMap = new HashMap<String,Integer>();
	}

	public void read() {
		try {
			File file = new File("1.txt");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String rs;
			while((rs=br.readLine())!=null) {
				if(rs.contains("代表罗马数字")) {
					String key = getStr(rs, "\".*\"").replace("\"", "");
					String value = getStr(rs, "[\\w]+");
					numMap.put(key, value);
				}else if(rs.matches("\".*\"份.*值 [\\d]+ 暗黑币")) {
					String wnum = getStr(rs, "\".*\"").replace("\"", "");
					wnum = replaceWnum(wnum);
					String tname = getStr(rs,"份.*值").replace("份", "").replace("值", "");
					int snum = Integer.valueOf(getStr(rs,"[\\d]+"));
					int num = conv(wnum);
					priceMap.put(tname, snum/num);
				}else if(rs.matches(".*是整数几.*")) {
					String wnum = getStr(rs, "\".*\"");
					int num = conv(replaceWnum(wnum.replace("\"", "")));
					System.out.println(wnum+"是整数"+num);
				}else if(rs.matches(".*值多少暗黑币.*")) {
					String wnum = getStr(rs, "\".*\"");
					int num = conv(replaceWnum(wnum.replace("\"", "")));
					String tname = getStr(rs,"份.*值");
					int price = num*priceMap.get(tname.replace("份", "").replace("值", ""));
					System.out.println(wnum+tname+price+"暗黑币");
				}else{
					System.out.println("我不知道你在说什么");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//文字符号替换
	public String replaceWnum(String wnum) {
		for(String key : numMap.keySet()) {
			wnum = wnum.replace(key, numMap.get(key));
		}
		return wnum;
	}
	
	//进制转化
	public int conv(String str) {
		int len = str.length();
		int sum = 0;
		
		for(int i=0 ;i<len ;i++){
			if(str.charAt(i)=="I".charAt(0)){
				if(i!=len-1&&(str.charAt(i+1)=="V".charAt(0)||str.charAt(i+1)=="X".charAt(0))){
					sum--;
				}else{
					sum++;
				}
			}
			
			if(str.charAt(i)=="V".charAt(0)){
				sum+=5;
			}
			
			if(str.charAt(i)=="X".charAt(0)){
				if(i!=len-1&&(str.charAt(i+1)=="L".charAt(0))){
					sum-=10;
				}else{
					sum+=10;
				}
			}
			
			if(str.charAt(i)=="L".charAt(0)){
				sum+=50;
			}
		}
		return sum;
	}

	//模糊查找
	public String getStr(String str,String regex) {
		String rs = "";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if(m.find()) {
			rs = str.substring(m.start(),m.end());
		}
		return rs;
	}
}