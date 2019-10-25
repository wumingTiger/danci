package com.example.wero.a1_20154816395.rxutils;

import com.example.wero.a1_20154816395.api.ApiException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-4-27
 */

public class RxExceeptionUtil {
    public static String exceptionHandler(Throwable e){
        String errMsg = "未知错误";
        if (e instanceof UnknownHostException){
            errMsg = "网络不可用";
        }else if (e instanceof SocketTimeoutException){
            errMsg = "连接超时";
        }else if (e instanceof JSONException ||
                e instanceof JsonSyntaxException){
            errMsg = "解析异常";
        }else if (e instanceof ApiException){
            ApiException apiException = (ApiException)e;
            errMsg = apiException.getMessage();
        }
        return errMsg;
    }

}
