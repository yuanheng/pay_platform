package com.bootdo.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class InvitationCodeGnerateUtil {

  public static String generateInvitationCode(){
    String sixCode = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
    Date date = new Date();
    String time = sdf.format(date);
    for (int i = 0; i < time.length() / 2; i++) {
      String singleCode;
      String x = time.substring(i * 2, (i + 1) * 2);
      int b = Integer.parseInt(x);
      if (b < 10) {
        singleCode = Integer.toHexString(Integer.parseInt(x));
      } else if (b >= 10 && b < 36) {
        singleCode = String.valueOf((char) (Integer.parseInt(x) + 55));
      } else {
        singleCode = String.valueOf((char) (Integer.parseInt(x) + 61));
      }
      sixCode = sixCode + singleCode;

    }
    System.out.println("生成一个6位不可重复的字符编码是：" + sixCode);
    return sixCode.toLowerCase();
  }

}
