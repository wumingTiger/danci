package com.example.wero.a1_20154816395.utils;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.rxutils.RxObserver;
import com.example.wero.a1_20154816395.rxutils.RxResultHelper;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;

import io.reactivex.disposables.Disposable;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-22
 */

public class StarWordHelper {
    private static boolean f;
    /**
     * 收藏单词
     */
    public static Disposable addStar(String word) {
        return RetrofitHelper.getRetrofit().starWord(ProApplication.USER.getId(), word)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribeWith(new RxObserver<Object>() {
                    @Override
                    public void _onNext(Object o) {
                        ToastUtils.showShort("收藏成功");
                    }

                    @Override
                    public void _onError(String errMsg) {
                        ToastUtils.showShort("收藏失败");
                    }
                });
    }

    public static Disposable cancleStar(String word){
        return RetrofitHelper.getRetrofit().cancleWord(ProApplication.USER.getId(), word)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribeWith(new RxObserver<Object>() {
                    @Override
                    public void _onNext(Object o) {
                        ToastUtils.showShort("取消成功");
                    }

                    @Override
                    public void _onError(String errMsg) {
                        ToastUtils.showShort("取消失败");
                    }
                });
    }

    /**
     * 单个单词
     * @param word
     * @return
     */
    public static boolean isStar(String word){
        RetrofitHelper.getRetrofit().isstarWord(ProApplication.USER.getId(), word)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribeWith(new RxObserver<Object>() {
                    @Override
                    public void _onNext(Object o) {
                        f = true;
                    }

                    @Override
                    public void _onError(String errMsg) {
                        f = false;
                    }
                });
        return f;
    }

    public static boolean checkStar(String word){
       return StarWordHelper.isStar(word);
    }
}
