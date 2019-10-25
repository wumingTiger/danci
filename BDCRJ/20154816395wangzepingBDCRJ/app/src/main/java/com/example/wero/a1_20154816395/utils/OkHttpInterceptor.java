package com.example.wero.a1_20154816395.utils;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wero on 18-4-25.
 */

public class OkHttpInterceptor implements Interceptor {

    private static final String TAG = "OkHttp";
    //设置有效期
    private static final long CACHE_CONTROL = 60 * 60 * 24 * 1;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(!NetworkUtils.isConnected()){    //未联网的情况下使用缓存
            request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        return null;
    }
}
