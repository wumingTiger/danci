package com.example.wero.a1_20154816395.api;

import com.example.wero.a1_20154816395.bean.DailyBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-20
 */

public interface DailyApi {
    @GET("dsapi/")
    Observable<DailyBean> getDailySentence(@Query("date") String time);
}
