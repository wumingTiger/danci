package com.example.wero.a1_20154816395.rxutils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by wero on 18-4-24.
 * 用于管理单个presenter的RxBus的事件，
 * 以及activity和fragment中RxJava相关代码的生命周期处理
 */

public class RxManager {
    private RxBus mRxBus;

    /**
     * 统一对所有的订阅者管理
     */
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * 获取RxBus的单例，进行管理
     */
    public RxManager(){
        mRxBus = RxBus.getInstance();
    }
    /**
     * 添加observer
     * @param observer
     */
    public void addObserver(DisposableObserver observer){
        if(observer!=null){
            mCompositeDisposable.add(observer);
        }
    }

    public void addObserver(Disposable observer){
        if(observer!=null){
            mCompositeDisposable.add(observer);
        }
    }

    /**
     * 单个presenter生命周期结束，取消订阅和所有rxbus观察
     */
    public void clear(){
        mCompositeDisposable.dispose();
    }

    /**
     * 对rxbus的简单封装
     * @param evenType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObserver (Class<T> evenType){
        return mRxBus.toObserver(evenType);
    }

}
