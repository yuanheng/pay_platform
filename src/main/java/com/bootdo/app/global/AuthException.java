package com.bootdo.app.global;

public class AuthException extends Exception {

    private String msg;
    private Integer status;

    public AuthException(String msg,Integer status){
        super(msg);
        this.status = status;
    }
}
