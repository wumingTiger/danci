package com.example.wero.a1_20154816395.mvp.addwordspack;

import com.example.wero.a1_20154816395.bean.WordPackBean;
import com.example.wero.a1_20154816395.mvp.IPresenter;
import com.example.wero.a1_20154816395.mvp.IView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-19
 */

public interface AddWordsPackContract {
    public interface View extends IView {
        void setHead(int acc, int count);
        void closeAct();
    }

    public interface Presenter extends IPresenter {
        void addWordPack(WordPackBean wordPackBean);
    }
}
