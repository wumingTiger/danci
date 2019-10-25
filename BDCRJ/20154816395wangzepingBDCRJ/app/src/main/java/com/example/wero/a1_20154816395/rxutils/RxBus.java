package com.example.wero.a1_20154816395.rxutils;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by wero on 18-4-24.
 * 统一管理RxJava，防止在activity，fragment，以及取消操作发生时，
 * 发生内存泄漏以及空指针
 * 单例模式
 */

public class RxBus {
    private static volatile RxBus instance;
    private final Subject<Object> bus;

    /**
     * 构造方法私有
     */
    private RxBus(){
        bus = PublishSubject.create().toSerialized();
    }

    /**
     * 获取单例
     * @return  单例的RxBus
     */
    public static RxBus getInstance() {
        if (instance == null){
            synchronized (RxBus.class){
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    /**
     * 发射事件
     * @param o 所有订阅该对象的observe都会接收事件
     */
    public void post (Object o){
        bus.onNext(o);
    }

    /**
     *
     * @param evenType 监听对象的类型
     * @param <T>
     * @return 返回一个T类型的observebal
     */
    public <T> Observable<T> toObserver(Class<T> evenType){
        return bus.ofType(evenType);
    }
}
