package com.example.wero.a1_20154816395.rxutils;

import android.util.Log;
import com.example.wero.a1_20154816395.BuildConfig;
import com.example.wero.a1_20154816395.api.ApiException;

import io.reactivex.observers.DisposableObserver;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-4-26
 */

public abstract class BaseObserver<T> extends DisposableObserver<T> {
    private String msg;
    @Override
    public void onNext(T t) {
        if(BuildConfig.DEBUG){
            Log.d("onNext: ", t.toString());
        }
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException){
            msg = "网络异常,请重试";
        }
        _onError(msg);
    }

    @Override
    public void onComplete() {
        _onComplete();
    }

    /**
     * 定义处理事件
     */
    public abstract void _onNext(T t);
    public abstract void _onError(String msg);
    public abstract void _onComplete();
}