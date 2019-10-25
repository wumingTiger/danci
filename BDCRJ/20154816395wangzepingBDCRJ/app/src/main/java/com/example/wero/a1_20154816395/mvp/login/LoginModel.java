package com.example.wero.a1_20154816395.mvp.login;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.mvp.IModel;
import com.example.wero.a1_20154816395.rxutils.RxObserver;
import com.example.wero.a1_20154816395.rxutils.RxResultHelper;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.utils.RetrofitHelper;

import io.reactivex.disposables.Disposable;

/**
 * Created by wero on 18-4-25.
 */

public class LoginModel implements IModel {
//    private boolean isLogin = false;

    /**
     * 处理登录业务
     * @param username
     * @param password
     * @param listener
     * @return
     */
    public Disposable loginModel(String username, String password, final OnLoginFinishedListener listener){
        if(listener == null){
            throw new RuntimeException("OnLongClickListener不能为空");
        }
        return RetrofitHelper.getRetrofit().login(username, password)
                .compose(RxSchedulersHelper.io_main())
                .subscribe(u ->{
                    ProApplication.USER = u.getContent();
                    listener.loginSuccess("登陆成功");
                }, e ->{
                    listener.loginFail("登录失败");
                });
    }


    /**
     * 登录回调
     */
    public interface OnLoginFinishedListener{
        /**
         * 登陆成功
         * @param str
         */
        void loginSuccess(String str);

        /**
         * 登录失败
         * @param str
         */
        void loginFail(String str);
    }
}
