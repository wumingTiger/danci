package com.example.wero.a1_20154816395.rxutils;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.wero.a1_20154816395.api.ApiException;
import com.example.wero.a1_20154816395.bean.BaseResult;
import com.example.wero.a1_20154816395.bean.ContentBean;
import com.example.wero.a1_20154816395.bean.WordPackBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-4-26
 */

public class RxResultHelper {

    /**
     * 这个封装有点问题, 不要用
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResult<T>, T> handleResult() {
        return new ObservableTransformer<BaseResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResult<T>> upstream) {
                return upstream.flatMap(new Function<BaseResult<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResult<T> response) throws Exception {
                        Log.d("----------", "h");
                        if (response == null) {
                            return Observable.empty();
                        } else if (response.getCode() == BaseResult.SUCCESS){
                            /* just要求返回的数据项不能为空值, 当服务器传回一个空值时,返回一个成功字符串 */
                            LogUtils.e(response.getContent());
                            T t = response.getContent();
                            if(t == null){
                                t = (T) "success";
                            }
                            return Observable.just(t);
                        }else {
                            //处理服务器返回的异常
                            return Observable.error(new ApiException(response.getCode()));
                        }
                    };
                });
            }
        };
    }

    /**
     * 解析异常,专门写转换类,这个可以使用
     * @return
     */
    public static ObservableTransformer<ContentBean, List<WordPackBean>> handleResultToWordPack() {
        return new ObservableTransformer<ContentBean, List<WordPackBean>>() {
            @Override
            public ObservableSource<List<WordPackBean>> apply(Observable<ContentBean> upstream) {
                return upstream.flatMap(new Function<ContentBean, ObservableSource<List<WordPackBean>>>() {
                    @Override
                    public ObservableSource<List<WordPackBean>> apply(ContentBean response) throws Exception {
                        Log.d("----------", "h");
                        if (response == null) {
                            return Observable.empty();
                        } else if (response.getCode() == BaseResult.SUCCESS){
                            /* just要求返回的数据项不能为空值, 当服务器传回一个空值时,返回一个成功字符串 */
                            LogUtils.e(response.getMessage());
                            List<WordPackBean> t = response.getContent();
                            if(t == null){
                                t = new ArrayList<>();
                            }
                            return Observable.just(t);
                        }else {
                            //处理服务器返回的异常
                            return Observable.error(new ApiException(response.getCode()));
                        }
                    };
                });
            }
        };
    }
}
