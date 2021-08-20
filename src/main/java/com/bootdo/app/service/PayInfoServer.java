package com.bootdo.app.service;

import com.bootdo.app.model.PayInfo;
import com.bootdo.app.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class PayInfoServer {

  private List<PayInfo> payInfos;
  @Autowired
  private RedisUtils redisUtils;

  public PayInfoServer(){
    payInfos = new ArrayList<>();
    PayInfo payInfo1 = new PayInfo("马明","北京银行","北京银行","6214680123569970");
    PayInfo payInfo2 = new PayInfo("马明","民生银行","民生银行","6226220131774852");
    PayInfo payInfo3 = new PayInfo("张超","邮政银行","邮政银行","6221807900005341294");
    PayInfo payInfo4 = new PayInfo("齐军","邮政银行","交通银行西安分行","6222620810027873385");
    payInfos.add(payInfo1);
    payInfos.add(payInfo2);
    payInfos.add(payInfo3);
    payInfos.add(payInfo4);
  }

  public PayInfo getPayInfo(){
    Set<Object> pay_infos = redisUtils.sGet("pay_infos_set");
    Iterator<Object> it = pay_infos.iterator();
    while (it.hasNext()) {
      List<Object> str = (ArrayList)it.next();
      int random=(int)(Math.random() * str.size());
      Object o = str.get(random);
      return (PayInfo) o;
    }
    return null;
  }


  public static void main(String[] args) {
    PayInfoServer payInfoServer = new PayInfoServer();
    for (int i = 0; i < 100 ; i++){
      PayInfo payInfo = payInfoServer.getPayInfo();
      System.out.println(payInfo.getBankName());
    }

  }
}
