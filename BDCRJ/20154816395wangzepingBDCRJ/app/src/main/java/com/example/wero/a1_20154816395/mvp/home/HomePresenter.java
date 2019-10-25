package com.example.wero.a1_20154816395.mvp.home;

import android.content.Context;
import android.content.SharedPreferences;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.base.BasePresenter;
import com.example.wero.a1_20154816395.bean.DailyBean;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.utils.RetrofitHelper;
import com.example.wero.a1_20154816395.utils.TimeUtils;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-18
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter{
    public HomePresenter(HomeContract.View view) {
        super(view);
    }

    @Override
    public void loadDaliySentence(String time) {
        mRxManager.addObserver(RetrofitHelper.getDailyApi().getDailySentence(time)
                .compose(RxSchedulersHelper.io_main())
                .subscribe(dailyBean->{
                    mView.setText(dailyBean.getContent(), dailyBean.getNote(), dailyBean.getTts());
                    mView.setImage(dailyBean.getPicture2());
                    mView.hideProgress();
                }, e ->{
                    mView.showFail(e.getMessage());
                }));
    }

    @Override
    public void loadDailyCount(){
        mRxManager.addObserver(RetrofitHelper.getRetrofit().getDailyCount(ProApplication.USER.getId(), TimeUtils.getShortDate())
                    .compose(RxSchedulersHelper.io_main())
                    .subscribe(baseResult -> {
                        ProApplication.EVERYDAY_COUNT = baseResult.getContent();
                        mView.setDailyCount(baseResult.getContent());
                    }, e -> {
                        mView.setDailyCount(ProApplication.EVERYDAY_COUNT);
                    }));
    }

    @Override
    public void loadTarget() {
        mRxManager.addObserver(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                int i;
                SharedPreferences preferences = ProApplication.getAppContext().getSharedPreferences("target", Context.MODE_PRIVATE);
                i = preferences.getInt("target", ProApplication.DEF_TARGET);
                emitter.onNext(i);
                emitter.onComplete();
            }
            }).compose(RxSchedulersHelper.io_main())
            .subscribe(integer -> {
                mView.setDialyTarget(integer);
            }));
    }

    @Override
    public void writeTarget(Integer integer) {
        mRxManager.addObserver(Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                //LogUtils.e(integer);
                SharedPreferences preferences = ProApplication.getAppContext().getSharedPreferences("target", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("target", integer);
                editor.commit();
                emitter.onNext(new Object());
            }
        }).compose(RxSchedulersHelper.io_main())
                .subscribe(o -> {
                    mView.showSuccess("修改每日目标成功");
                }));
    }
}
