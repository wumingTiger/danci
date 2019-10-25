package com.example.wero.a1_20154816395.mvp.listen;

import com.example.wero.a1_20154816395.mvp.IPresenter;
import com.example.wero.a1_20154816395.mvp.IView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-19
 */

public interface ListenContract {
    public interface View extends IView {
        void setImgPlaying();
        void setImgUnPlaying();
    }

    public interface Presenter extends IPresenter {

    }
}
