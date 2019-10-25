package com.example.wero.a1_20154816395.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.rxutils.RxManager;
import com.example.wero.a1_20154816395.ui.ProgressDialog;

import butterknife.ButterKnife;

/**
 * Created by wero on 18-4-24.
 */

public abstract class BaseFragment extends Fragment {
    protected RxManager mRxMannager = new RxManager();
    protected ProgressDialog mProgressDialog;
    protected View rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, rootView);
        mProgressDialog =  new ProgressDialog(getContext());
        return rootView;
    }

    /**
     * 获取布局文件
     * @return
     */
    protected abstract int getLayoutResource();

    /**
     * 初始化
     */
    protected abstract void initView();
    @Override
    public void onDestroy() {
        mRxMannager.clear();
        super.onDestroy();
    }
    /**
     * 通知
     * @param toast
     */
    protected void showToast(String toast){
        Toast.makeText(ProApplication.getAppContext(), toast, Toast.LENGTH_SHORT).show();
    }
}
