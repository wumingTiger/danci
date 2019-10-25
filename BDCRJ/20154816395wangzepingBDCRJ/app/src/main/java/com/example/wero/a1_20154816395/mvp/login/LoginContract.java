package com.example.wero.a1_20154816395.mvp.login;

import com.example.wero.a1_20154816395.mvp.IPresenter;
import com.example.wero.a1_20154816395.mvp.IView;

/**
 * Created by wero on 18-4-25.
 * 登录界面的contract
 */

public interface LoginContract {

    public interface View extends IView{
        /**
         * 登录成功
         */
        void loginSuccess();

        /**
         * 登录失败
         */
        void loginFail(String failMsg);
        void registerFail(String failMsg);
        /**
         * 显示登录进度
         */
        void showProgress();

        /**
         * 隐藏
         */
        void hideProgress();
    }

    public interface Presenter extends IPresenter{
        /**
         * 登录功能
         * @param username
         * @param password
         */
        void login(String username, String password);

        /**
         * 检查输入账号是否合法
         */
        boolean checkAccount(String username, String password);

        /**
         * 注册
         * @param username
         * @param password
         */
        void register(String username, String password);
    }
}
