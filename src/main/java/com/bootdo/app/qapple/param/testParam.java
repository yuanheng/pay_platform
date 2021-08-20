package com.bootdo.app.qapple.param;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class testParam {
    /**"商家账号*/
    private String merchantName;
    /**青苹果出款订单号*/
    private String tradeNo;

    /**"商家密码"*/
    private String password;

    /**"签名类型:RSA"*/
    private String signType;

    /**"签名参数"*/
    private String sign;

    public testParam(String merchantName, String tradeNo, String password, String signType, String sign) {
        this.merchantName = merchantName;
        this.tradeNo = tradeNo;
        this.password = password;
        this.signType = signType;
        this.sign = sign;
    }

    public testParam() {

    }

    public static Map<String, Object> toMap(Object bean) throws Exception {

        Class type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    //returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;

    }

    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, Object> sortMap(Map<String, Object> map ) {
        Map<String, Object> newMap = new HashMap<>();
        Set set=map.keySet();
        Object[] arr=set.toArray();
        Arrays.sort(arr);
        for(Object key:arr){
            newMap.put(key.toString(),map.get(key));
        }
        return newMap;

    }

//    public static void main(String[] args) {
//        testParam testParam = new testParam("商家","201092121","123456","RSA","xxxxx");
//
//        try {
//            System.out.println(toMap(testParam).toString());
//            System.out.println(object2Map(testParam).toString());
//            System.out.println(sortMap(object2Map(testParam)).toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
