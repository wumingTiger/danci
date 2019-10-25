package com.example.wero.a1_20154816395.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wero.a1_20154816395.base.BaseActivity;
import com.example.wero.a1_20154816395.base.BasePresenter;

/**
 * Created by wero on 18-4-25.
 * mvpactivity的基类
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;

    /**
     * 创建
     * @return
     */
    protected  abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        initView();
    }

    @Override
    protected void onDestroy() {
        if(mPresenter != null){
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }
}
