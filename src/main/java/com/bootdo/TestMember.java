package com.bootdo;

import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.TradeLogDO;

import java.util.Date;

public class TestMember {

    public void run(MemberDO memberDO){
        System.out.println(memberDO.getUsername() + " 当前线程："+Thread.currentThread().getName());

        synchronized (memberDO.getUsername().intern()){
            System.out.println(memberDO.getUsername() + " in 当前线程："+Thread.currentThread().getName());
            try{
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(memberDO.getUsername() + " ok 当前线程："+Thread.currentThread().getName());

        }

        synchronized (memberDO.getUsername().intern()){
            System.out.println(memberDO.getUsername() + " in 6666666666当前线程："+Thread.currentThread().getName());
            try{
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(memberDO.getUsername() + " ok 88888888888888当前线程："+Thread.currentThread().getName());

        }
    }

    public void run2(MemberDO memberDO){
        System.out.println(memberDO.getUsername() + " 123123当前线程："+Thread.currentThread().getName());

        synchronized (memberDO.getUsername().intern()){
            System.out.println(memberDO.getUsername() + " in 123123当前线程："+Thread.currentThread().getName());
            try{
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(memberDO.getUsername() + " ok 123123当前线程："+Thread.currentThread().getName());

        }


        synchronized (memberDO.getUsername().intern()){
            System.out.println(memberDO.getUsername() + " in 555555555当前线程："+Thread.currentThread().getName());
            try{
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(memberDO.getUsername() + " ok 77777777777777当前线程："+Thread.currentThread().getName());

        }
    }


    public static void main(String[] args) {



        TradeLogDO tradeLogDO = new TradeLogDO.Builder()
                                        .agreeId(1)
                                        .amount("1000")
                                        .createTime(new Date()).build();

        System.out.println(tradeLogDO);
    }
}
