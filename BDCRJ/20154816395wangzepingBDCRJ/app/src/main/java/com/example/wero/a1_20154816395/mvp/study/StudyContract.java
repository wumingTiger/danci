package com.example.wero.a1_20154816395.mvp.study;

import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.mvp.IPresenter;
import com.example.wero.a1_20154816395.mvp.IView;

import java.util.List;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-18
 */

public interface StudyContract {
    public interface Presenter extends IPresenter {
        void updateDailyCount();
        void initData(String string);
    }

    public interface View extends IView {
        void showErrorDialog(String s1);
    }
}
