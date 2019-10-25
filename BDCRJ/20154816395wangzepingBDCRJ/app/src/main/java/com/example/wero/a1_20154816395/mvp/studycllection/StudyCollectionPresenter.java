package com.example.wero.a1_20154816395.mvp.studycllection;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.base.BasePresenter;
import com.example.wero.a1_20154816395.rxutils.RxObserver;
import com.example.wero.a1_20154816395.rxutils.RxResultHelper;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.utils.RetrofitHelper;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-10
 */

public class StudyCollectionPresenter extends BasePresenter<StudyCollectionContract.View> implements StudyCollectionContract.Presenter{

    public StudyCollectionPresenter(StudyCollectionContract.View view) {
        super(view);
    }


    @Override
    public void loadWordPack() {
        mView.showProgress();
        mRxManager.addObserver(RetrofitHelper.getRetrofit().getWordPacks(ProApplication.USER.getId())
                    .compose(RxSchedulersHelper.io_main())
                    .compose(RxResultHelper.handleResultToWordPack())
                    .subscribe(w -> {
                        mView.hideProgress();
                        mView.showWordPack(w);
                    }, e ->{
                        mView.hideProgress();
                        ToastUtils.showShort(e.getMessage());
                    } ));
    }
}
