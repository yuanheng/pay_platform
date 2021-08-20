package com.bootdo.app.qapple.vo;

public class BaseResponse {
    /** 状态码: 状态码: 200:操作成功;500:系统异常 613 验签不通过 */
    private Integer code;
    /** 提示消息 */
    private String message;

    public BaseResponse(Integer code, String message){
        this.code = code;
        this.message = message;
    }
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
}
