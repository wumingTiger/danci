package com.example.wero.a1_20154816395.api;

import com.example.wero.a1_20154816395.bean.BaseResult;
import com.example.wero.a1_20154816395.bean.ContentBean;
import com.example.wero.a1_20154816395.bean.UserBean;
import com.example.wero.a1_20154816395.bean.UserStudyInfoBean;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.bean.WordPackBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wero on 18-4-24.
 */

public interface ApiService {

    /**
     * 登录
     * @return
     */
    @POST("login")
    @FormUrlEncoded
    Observable<BaseResult<UserBean>> login(@Field("username") String username, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Observable<BaseResult<UserBean>> register(@Field("username") String username, @Field("password") String password);

    /**
     * 收藏单词
     * @param userid
     * @param word
     * @return
     */
    @POST("starword")
    @FormUrlEncoded
    Observable<BaseResult<Object>> starWord(@Field("userid") Long userid, @Field("word") String word);

    /**
     * 取消收藏
     * @param userid
     * @param word
     * @return
     */
    @POST("cancleword")
    @FormUrlEncoded
    Observable<BaseResult<Object>> cancleWord(@Field("userid") Long userid, @Field("word") String word);

    /**
     * 验证单词是否收藏
     * @param userid
     * @param word
     * @return
     */
    @POST("isstar")
    @FormUrlEncoded
    Observable<BaseResult<Object>> isstarWord(@Field("userid") Long userid, @Field("word") String word);

    @POST("getwordpacks")
    @FormUrlEncoded
    Observable<ContentBean> getWordPacks(@Field("userid") Long userid);

    @POST("getwordpack")
    @FormUrlEncoded
    Observable<BaseResult<List<WordBean>>> getWordPack(@Field("userid") Long userid, @Field("packname") String packname);

    /**
     * 这里实际是post json对象
     * @param body
     * @return
     */
    @POST("addwordpack")
    Observable<BaseResult<Object>> addWordPack(@Body WordPackBean body);

    /**
     * 这里实际是post json对象
     * @param body
     * @return
     */
    @POST("alterwordpack")
    Observable<BaseResult<Object>> alterWordPack(@Body WordPackBean body);

    /**
     * 记录用户的学习情况
     * @param userid
     * @return
     */

    @POST("updatedailycount")
    @FormUrlEncoded
    Observable<BaseResult<Integer>> updateDailyCount(@Field("userid") Long userid, @Field("date") String date, @Field("count") int count);

    @POST("getdailycount")
    @FormUrlEncoded
    Observable<BaseResult<Integer>> getDailyCount(@Field("userid") Long userid, @Field("date") String date);

    @POST("getuserstudyinfo")
    @FormUrlEncoded
    Observable<BaseResult<List<UserStudyInfoBean>>> getUserStudyInfo(@Field("userid") Long userid);


}
