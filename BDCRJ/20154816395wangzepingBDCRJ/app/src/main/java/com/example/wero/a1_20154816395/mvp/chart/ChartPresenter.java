package com.example.wero.a1_20154816395.mvp.chart;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.base.BasePresenter;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.utils.RetrofitHelper;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-17
 */

public class ChartPresenter extends BasePresenter<ChartContract.View> implements ChartContract.Presenter{
    public ChartPresenter(ChartContract.View view) {
        super(view);
    }

    public void loadUserStudyInfo(){
        mView.showProgress();
        mRxManager.addObserver(RetrofitHelper.getRetrofit().getUserStudyInfo(ProApplication.USER.getId())
                    .compose(RxSchedulersHelper.io_main())
                    .subscribe(lu -> {
                        mView.hideProgress();
                        mView.setData(lu.getContent());
                    }, e->{
                        mView.hideProgress();
                        ToastUtils.showShort(e.getMessage());
                    }));
    }
}
