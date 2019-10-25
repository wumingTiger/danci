package com.example.wero.a1_20154816395.mvp.home;

import com.example.wero.a1_20154816395.mvp.IPresenter;
import com.example.wero.a1_20154816395.mvp.IView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-18
 */

public interface HomeContract {
    public interface Presenter extends IPresenter {
        void loadDaliySentence(String time);
        void loadDailyCount();
        void loadTarget();
        void writeTarget(Integer integer);
    }

    public interface View extends IView {
        void autoSetVoiceIcon();
        void showSuccess(String str);
        void showFail(String str);
        void setImage(String url);
        void setText(String en ,String ch, String tts);
        void setDailyCount(int count);
        void setDialyTarget(int target);
    }
}
