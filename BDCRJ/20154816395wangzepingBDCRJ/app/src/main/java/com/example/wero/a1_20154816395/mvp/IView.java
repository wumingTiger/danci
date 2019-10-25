package com.example.wero.a1_20154816395.mvp;

/**
 * Created by wero on 18-4-24.
 * v层的base
 * mvp, v
 * 外部层提供统一调用的规范
 */

public interface IView {

    /**
     * 显示登录进度
     */
    void showProgress();

    /**
     * 隐藏
     */
    void hideProgress();
}
