package com.example.wero.a1_20154816395.api;

import com.example.wero.a1_20154816395.bean.BaseResult;

/**
 * Created by wero on 18-4-25.
 * 自定义网络请求错误函数
 */

public class ApiException extends RuntimeException {
    private static String message;

    public ApiException(int resultCode){
        this(getApiException(resultCode));
    }

    public ApiException(String detailMessage){
        super(detailMessage);
    }

    @Override
    public String getMessage() {
        return message;
    }

    private static String getApiException(int code){
        switch (code){
            case BaseResult.USERNAME_OR_PASSWORD_ERROR:
                message = "密码错误";
                break;
            case BaseResult.USER_IS_REGISTER:
                message = "用户未注册";
                break;
            case BaseResult.USER_NOT_FOUND:
                message = "该用户不存在";
                break;
            default:
                message = "未知错误";
                break;
        }
        return message;
    }
}
