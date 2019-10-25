package com.example.wero.a1_20154816395.bean;


/**
 * Created by wero on 18-4-24.
 * 基类的bean，T
 * 根据服务器的接口规则，创建一个公用的接口数据类
 */

public class BaseResult<T> {
    public static final int SUCCESS = 1000;
    public static final int USERNAME_OR_PASSWORD_ERROR = 1001;
    public static final int USER_NOT_FOUND = 1002;
    public static final int USER_IS_REGISTER = 1003;
    private int code;
    private String message;
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "BaseResult{" + "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + content + '}';
    }
}
