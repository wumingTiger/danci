package com.example.wero.a1_20154816395.utils;

import com.example.wero.a1_20154816395.BuildConfig;
import com.example.wero.a1_20154816395.api.ApiService;
import com.example.wero.a1_20154816395.api.DailyApi;
import com.example.wero.a1_20154816395.api.WordApi;
import com.example.wero.a1_20154816395.bean.QueryWordBean;
import com.example.wero.a1_20154816395.ui.StateButton;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wero on 18-4-24.
 * 封装了OkHttp与Retrofit
 * 简化请求过程
 */

public class RetrofitHelper {
    //设置最大超时时间
    private static final long TIME_OUT = 10;
    private static final String BASE_URL = "http://120.79.213.244:8080/word-0.0.1/";
    private static final String DAILY = "http://open.iciba.com/";
    private static final String QUERY_WORD = "http://dict-co.iciba.com/";
    private static ApiService apiService;
    private static DailyApi dailyApi;
    private static WordApi wordApi;

    //构造方法私有，不允许构造
    private RetrofitHelper(){}

    /**
     *  访问自己的服务器
     * @return 返回封装好Retrofit的api
     */
    public static ApiService getRetrofit(){
        String url = BASE_URL;
        if(apiService == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if(BuildConfig.DEBUG){
                //Log信息拦截，便于调试
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                //设置Log模式
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }

            OkHttpClient okHttpClient = builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build();

//            if (BuildConfig.DEBUG){
//                url = LOCAL_URL;
//            }

            Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    //添加Gson转换器
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加Retrofit到RxJava的转换器
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            apiService = mRetrofit.create(ApiService.class);
        }

        return apiService;
    }

    public static DailyApi getDailyApi(){
        String url = DAILY;
        if(dailyApi == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if(BuildConfig.DEBUG){
                //Log信息拦截，便于调试
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                //设置Log模式
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }

            OkHttpClient okHttpClient = builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build();

//            if (BuildConfig.DEBUG){
//                url = LOCAL_URL;
//            }

            Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    //添加Gson转换器
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加Retrofit到RxJava的转换器
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            dailyApi = mRetrofit.create(DailyApi.class);
        }

        return dailyApi;
    }


    public static WordApi getWordApi(){
        String url = QUERY_WORD;
        if(wordApi == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if(BuildConfig.DEBUG){
                //Log信息拦截，便于调试
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                //设置Log模式
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }

            OkHttpClient okHttpClient = builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build();

//            if (BuildConfig.DEBUG){
//                url = LOCAL_URL;
//            }

            Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    //添加Gson转换器
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加Retrofit到RxJava的转换器
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            wordApi = mRetrofit.create(WordApi.class);
        }

        return wordApi;
    }

}
