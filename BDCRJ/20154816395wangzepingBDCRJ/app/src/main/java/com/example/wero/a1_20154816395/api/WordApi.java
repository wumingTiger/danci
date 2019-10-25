package com.example.wero.a1_20154816395.api;

import com.example.wero.a1_20154816395.bean.DailyBean;
import com.example.wero.a1_20154816395.bean.QueryWordBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-21
 */

public interface WordApi {
    public  static String KEY = "256FC03930490CF7F18BF53EE2433A54";
    @GET("api/dictionary.php")
    Observable<QueryWordBean> getWord(@Query("w") String word, @Query("type") String type, @Query("key") String key);
}
