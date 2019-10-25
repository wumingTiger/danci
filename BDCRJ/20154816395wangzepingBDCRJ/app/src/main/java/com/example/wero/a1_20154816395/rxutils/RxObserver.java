package com.example.wero.a1_20154816395.rxutils;

import android.util.Log;

import com.example.wero.a1_20154816395.BuildConfig;

import io.reactivex.observers.DisposableObserver;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-4-27
 */

public abstract class RxObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {
        if(BuildConfig.DEBUG){
            Log.d("onNext: ", t.toString());
        }
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        Log.d("error: ", e.toString());
        _onError(RxExceeptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {
//        _onComplete();
    }

    /**
     * 定义处理事件
     */
    public abstract void _onNext(T t);
    public abstract void _onError(String errMsg);
//    public abstract void _onComplete();
}
