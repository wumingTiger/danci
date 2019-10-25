package com.example.wero.a1_20154816395.mvp.study;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.base.BasePresenter;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.utils.RetrofitHelper;
import com.example.wero.a1_20154816395.utils.TimeUtils;

import io.reactivex.functions.Consumer;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-18
 */

public class StudyPresenter extends BasePresenter<StudyContract.View> implements StudyContract.Presenter{
    public StudyPresenter(StudyContract.View view) {
        super(view);
    }
    public void updateDailyCount(){
        mRxManager.addObserver(RetrofitHelper.getRetrofit().updateDailyCount(ProApplication.USER.getId(), TimeUtils.getShortDate(), ProApplication.EVERYDAY_COUNT)
                    .compose(RxSchedulersHelper.io_main())
                    .subscribe( integerBaseResult -> {
                        ToastUtils.showShort("今天已经学了" + integerBaseResult.getContent() + "个单词啦");
                    }, e -> {
                        ToastUtils.showShort("数据同步失败, 学习记录可能未能保存");
                    }));
    }

    @Override
    public void initData(String string) {
        mView.showProgress();
        mRxManager.addObserver(RetrofitHelper.getRetrofit().getWordPack(ProApplication.USER.getId(), string)
                        .compose(RxSchedulersHelper.io_main())
                        .subscribe(wordlist -> {
                            mView.hideProgress();
                        }, e -> {
                            mView.hideProgress();
                            ToastUtils.showShort(e.getMessage());
                        }));
    }
}
