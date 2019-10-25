package com.example.wero.a1_20154816395.mvp.cards;

import com.example.wero.a1_20154816395.mvp.IPresenter;
import com.example.wero.a1_20154816395.mvp.IView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-18
 */

public interface CardsContract {
    public interface View extends IView {
        void setHead(float x, float y);
    }

    public interface Presenter extends IPresenter {

    }
}
