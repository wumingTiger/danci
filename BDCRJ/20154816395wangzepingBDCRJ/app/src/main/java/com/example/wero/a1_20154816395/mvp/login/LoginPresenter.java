package com.example.wero.a1_20154816395.mvp.login;

import android.text.TextUtils;

import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.base.BasePresenter;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.utils.RetrofitHelper;

/**
 * Created by wero on 18-4-25.
 *
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{

    public LoginPresenter(LoginContract.View view){
        super(view);
    }

    @Override
    public void login(String username, String password) {
        if (!checkAccount(username, password)){
            mRxManager.addObserver(new LoginModel().loginModel(username, password, new LoginModel.OnLoginFinishedListener() {
                @Override
                public void loginSuccess(String str) {
                    mView.hideProgress();
                    mView.loginSuccess();
                }

                @Override
                public void loginFail(String str) {
                    mView.hideProgress();
                    mView.loginFail(str);
                }
            }));
        }else{
            mView.hideProgress();
            mView.loginFail("请输入密码");
        }
    }

    @Override
    public boolean checkAccount(String username, String password) {
        return TextUtils.isEmpty(username) || TextUtils.isEmpty(password);
    }

    @Override
    public void register(String username, String password) {
        if (!checkAccount(username, password)){
            mRxManager.addObserver(RetrofitHelper.getRetrofit().register(username, password)
                        .compose(RxSchedulersHelper.io_main())
                        .subscribe(baseResult -> {
                            ProApplication.USER = baseResult.getContent();
                            mView.hideProgress();
                            mView.loginSuccess();
                        }, e->{
                            mView.hideProgress();
                            mView.registerFail(e.getMessage());
                        }));
        }else{
            mView.hideProgress();
            mView.loginFail("请输入密码");
        }
    }
}
