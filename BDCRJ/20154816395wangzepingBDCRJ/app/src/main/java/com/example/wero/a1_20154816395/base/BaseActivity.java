package com.example.wero.a1_20154816395.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.rxutils.RxManager;
import com.example.wero.a1_20154816395.ui.ProgressDialog;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by wero on 18-4-24.
 * 封装一些基本的，常用的方法，减少代码重复
 * 不带MVp功能,简单的封装
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected RxManager mRxMannager = new RxManager();
    protected ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        config();
        mProgressDialog = new ProgressDialog(this);
    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }
    /**
     * 取消绑定，防止内存泄漏
     */
    @Override
    protected void onDestroy() {
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
    /**
     * 初始化
     */
    protected abstract void initView();
    /**
     * 获取布局文件
     * @return
     */
    protected abstract int getLayoutResource();

    protected void config(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }
}
