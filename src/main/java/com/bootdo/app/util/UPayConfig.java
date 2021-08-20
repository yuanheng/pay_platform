package com.bootdo.app.util;

public class UPayConfig {

    // 应用ID,您的APPID

    public static String app_id  = "705791423286345728";

    // 商户私钥

    public static String private_key = "E461857651F1235F2DC62A39B80DB273";


    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.yikamile.com:6868/api/upaynotify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://192.168.1.102:8082/demo/return";

    // 签名方式
    public static String sign_type = "MD5";

    // 支付网关
    public static String gatewayUrl = "http://pay.allbtpay.com/api/open/pay";



}
