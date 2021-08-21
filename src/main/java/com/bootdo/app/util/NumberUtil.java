package com.bootdo.app.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PseudoColumnUsage;

public class NumberUtil {

    public static String add(String str1,String str2){
        if(str1 == null){
            str1 = "0.0";
        }
        if(str2 == null){
            str2 = "0.0";
        }
        BigDecimal num1 = new BigDecimal(str1);
        BigDecimal num2 = new BigDecimal(str2);
        BigDecimal result = num1.add(num2);
        return result.setScale(4,BigDecimal.ROUND_UP).toString();
    }



    public static String multiply(String str1,String str2){
        if(str1 == null){
            str1 = "0.0";
        }
        if(str2 == null){
            str2 = "0.0";
        }
        BigDecimal num1 = new BigDecimal(str1);
        BigDecimal num2 = new BigDecimal(str2);
        BigDecimal result = num1.multiply(num2);
        return result.setScale(4,BigDecimal.ROUND_UP).toString();
    }

    public static String divide(String str1,String str2){
        if(str1 == null){
            str1 = "0.0";
        }
        if(str2 == null){
            str2 = "0.0";
        }
        BigDecimal num1 = new BigDecimal(str1);
        BigDecimal num2 = new BigDecimal(str2);
        BigDecimal result = num1.divide(num2, 2,BigDecimal.ROUND_DOWN);
        return result.toPlainString();
    }

    public static String subtract(String str1,String str2){
        if(str1 == null || str1.length() == 0){
            str1 = "0.0";
        }
        if(str2 == null || str2.length() == 0){
            str2 = "0.0";
        }
        BigDecimal num1 = new BigDecimal(str1);
        BigDecimal num2 = new BigDecimal(str2);
        BigDecimal result = num1.subtract(num2);
        return result.setScale(4,BigDecimal.ROUND_UP).toString();
    }

    public static int compare(String str1, String str2) {
        if(str1 == null){
            str1 = "0.0";
        }
        if(str2 == null){
            str2 = "0.0";
        }
        BigDecimal num1 = new BigDecimal(str1);
        BigDecimal num2 = new BigDecimal(str2);
        return num1.compareTo(num2);
    }


    public static void main(String[] args) {
        System.out.println(NumberUtil.divide("6.0","1700"));
    }


}
