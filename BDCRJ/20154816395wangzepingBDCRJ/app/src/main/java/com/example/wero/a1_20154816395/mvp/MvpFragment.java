package com.example.wero.a1_20154816395.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wero.a1_20154816395.base.BaseFragment;
import com.example.wero.a1_20154816395.base.BasePresenter;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-10
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mPresenter;

    /**
     * 创建
     * @return
     */
    protected  abstract P createPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mPresenter = createPresenter();
        initView();
        return rootView;
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
