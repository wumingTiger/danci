package com.example.wero.a1_20154816395.mvp.studycllection;

import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.bean.WordPackBean;
import com.example.wero.a1_20154816395.mvp.IPresenter;
import com.example.wero.a1_20154816395.mvp.IView;

import java.util.List;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-10
 */

public interface StudyCollectionContract {
    public interface View extends IView {
        void showWordPack(List<WordPackBean> wordPackBeans);
        void showSuccess(String str);
        void showFail(String str);
    }

    public interface Presenter extends IPresenter {
        void loadWordPack();
    }
}
