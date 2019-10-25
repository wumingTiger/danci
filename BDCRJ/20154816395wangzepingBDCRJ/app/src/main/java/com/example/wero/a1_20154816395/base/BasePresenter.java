package com.example.wero.a1_20154816395.base;

import android.util.Log;

import com.example.wero.a1_20154816395.mvp.IView;
import com.example.wero.a1_20154816395.rxutils.RxManager;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by wero on 18-4-24.
 * BasePresent，封装了一些基本方法：
 * 对引用的管理，防止内存泄露
 */

public abstract class BasePresenter<V extends IView>{
    private String TAG = this.getClass().getName();
    protected V mView;
    protected RxManager mRxManager = new RxManager();

    public BasePresenter(V view){
        attach(view);
    }
    /**
     * 绑定view
     * @param view
     */
    public void attach(V view) {
        this.mView = view;
    }

    /**
     * 解除绑定，以避免内存泄漏
     */
    public void onDestroy() {
        this.mView = null;
        onUnSubscribe();
    }
    /**
     *
     * @return 获取View
     */
    public V getView(){
        return mView;
    }
    /**
     * RxJava取消注册，以避免内存泄漏
     */
    public void onUnSubscribe() {
        Log.e(TAG, "clear");
        if (mRxManager != null) {
            mRxManager.clear();
        }
    }
}
