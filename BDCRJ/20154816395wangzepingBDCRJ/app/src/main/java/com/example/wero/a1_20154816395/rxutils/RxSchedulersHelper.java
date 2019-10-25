package com.example.wero.a1_20154816395.rxutils;

import com.example.wero.a1_20154816395.api.ApiException;
import com.example.wero.a1_20154816395.bean.BaseResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-4-26
 */

public class RxSchedulersHelper {

    /**
     * io线程到主线程
     * @param <T>
     * @return
     */
    public static <T>ObservableTransformer<T, T> io_main(){
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
