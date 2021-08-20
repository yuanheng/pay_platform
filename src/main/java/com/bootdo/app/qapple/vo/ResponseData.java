package com.bootdo.app.qapple.vo;

/**
 * 请求响应对象
 * @param <T>
 */
public class ResponseData<T>{
    /** 业务结果数据 */
    protected T data;

    /** 消息调试内容 */
    private String msgDebug;
    /** 调用跟踪号 */
    private String transNo;

    /** 状态码: 状态码: 200:操作成功;500:系统异常 613 验签不通过 */
    private Integer code;
    /** 提示消息 */
    private String message;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsgDebug() {
        return msgDebug;
    }

    public void setMsgDebug(String msgDebug) {
        this.msgDebug = msgDebug;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }
}
