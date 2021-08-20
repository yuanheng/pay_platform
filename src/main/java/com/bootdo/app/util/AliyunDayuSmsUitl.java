// This file is auto-generated, don't edit it. Thanks.
package com.bootdo.app.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;

public class AliyunDayuSmsUitl {


    public static com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        Config config = new Config();
        config.accessKeyId = "LTAI5t7sf616QPtPdNee4zji";;
        config.accessKeySecret = "tM4HiIOVzgVL9Tq2iszfLq1nvEX3zq";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static String sendSms(String phoneNum, String signName, String templateCode, String params) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = AliyunDayuSmsUitl.createClient();
        // 1.发送短信
        SendSmsRequest sendReq = new SendSmsRequest()
                .setPhoneNumbers(phoneNum)
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam(params);
        SendSmsResponse sendResp = client.sendSms(sendReq);

        String code = sendResp.body.code;

        if (!com.aliyun.teautil.Common.equalString(code, "OK")) {
            com.aliyun.teaconsole.Client.log("错误信息: " + sendResp.body.message + "");
            return code;
        }
        com.aliyun.teaconsole.Client.log("success: " + sendResp.body.message + "");
        return code;
    }


    public static void main(String[] args) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","8888");
        sendSms("13070104069","阿里云通信","SMS_215170122",jsonObject.toJSONString());
    }

}
