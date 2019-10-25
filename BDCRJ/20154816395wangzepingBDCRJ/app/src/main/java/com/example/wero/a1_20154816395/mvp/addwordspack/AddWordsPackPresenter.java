package com.example.wero.a1_20154816395.mvp.addwordspack;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.base.BasePresenter;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.bean.WordPackBean;
import com.example.wero.a1_20154816395.rxutils.RxObserver;
import com.example.wero.a1_20154816395.rxutils.RxResultHelper;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.utils.RetrofitHelper;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-19
 */

public class AddWordsPackPresenter extends BasePresenter<AddWordsPackContract.View> implements AddWordsPackContract.Presenter{
    public AddWordsPackPresenter(AddWordsPackContract.View view) {
        super(view);
    }

    @Override
    public void addWordPack(WordPackBean wordPackBean) {
        mView.showProgress();
        mRxManager.addObserver(RetrofitHelper.getRetrofit().addWordPack(wordPackBean)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribeWith(new RxObserver<Object>() {
                    @Override
                    public void _onNext(Object o) {
                        mView.hideProgress();
                        ToastUtils.showShort("success");
                        mView.closeAct();
                    }

                    @Override
                    public void _onError(String errMsg) {
                        mView.hideProgress();
                        ToastUtils.showShort("failed");
                    }
                }));
    }
}
