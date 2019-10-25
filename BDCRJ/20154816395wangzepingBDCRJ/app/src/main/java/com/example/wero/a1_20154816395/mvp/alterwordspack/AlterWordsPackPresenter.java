package com.example.wero.a1_20154816395.mvp.alterwordspack;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.base.BasePresenter;
import com.example.wero.a1_20154816395.bean.WordPackBean;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.utils.RetrofitHelper;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-19
 */

public class AlterWordsPackPresenter extends BasePresenter<AlterWordsPackContract.View> implements AlterWordsPackContract.Presenter{

    public AlterWordsPackPresenter(AlterWordsPackContract.View view) {
        super(view);
    }

    @Override
    public void alterWordPack(WordPackBean wordPackBean) {
        mView.showProgress();
        mRxManager.addObserver(RetrofitHelper.getRetrofit().alterWordPack(wordPackBean)
                    .compose(RxSchedulersHelper.io_main())
                    .subscribe(objectBaseResult -> {
                        mView.hideProgress();
                        if (objectBaseResult.getCode() == 1000){
                            ToastUtils.showShort("success");
                            mView.closeAct();
                        }else {
                            ToastUtils.showShort("failed");
                        }
                    }, e ->{
                        ToastUtils.showShort("failed");
                        mView.hideProgress();
                    }));
    }
}
