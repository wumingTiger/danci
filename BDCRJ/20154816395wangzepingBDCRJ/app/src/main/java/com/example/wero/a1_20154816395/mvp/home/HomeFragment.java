package com.example.wero.a1_20154816395.mvp.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.mvp.MvpFragment;
import com.example.wero.a1_20154816395.service.AudioService;
import com.example.wero.a1_20154816395.utils.PlayComplete;
import com.example.wero.a1_20154816395.utils.TimeUtils;

import java.util.Date;
import butterknife.BindView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-20
 */

public class HomeFragment extends MvpFragment<HomePresenter> implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{
    @BindView(R.id.srefresh_home) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.image_daily) ImageView image_daily;
    @BindView(R.id.tv_daily_en) TextView tv_daily_en;
    @BindView(R.id.tv_daily_ch) TextView tv_daily_ch;
    @BindView(R.id.btn_last) Button btn_last;
    @BindView(R.id.btn_home_alert) Button btn_home_alert;
    @BindView(R.id.ib_home_voice) ImageButton ib_home_voice;
    @BindView(R.id.et_home_daylicount) EditText et_home_daylicount;
    @BindView(R.id.tv_comp) TextView tv_comp;
    private Date date;
    private String voiceUrl;

    @Override
    protected void initView() {
        date = new Date();
        ib_home_voice.setOnClickListener(this);
        btn_last.setOnClickListener(this);
        autoSetVoiceIcon();
        loadData();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.cfore2, R.color.cfore1);
        btn_home_alert.setOnClickListener(this);
    }

    private void loadData(){
        mPresenter.loadDaliySentence(TimeUtils.getShortDate(date, 0l));
        mPresenter.loadDailyCount();
        mPresenter.loadTarget();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    /**
     * 点击播放
     */
    public void playVolum(){
        ib_home_voice.setImageResource(R.drawable.voice2);
        AudioService.startVoiceByService(getActivity(), voiceUrl);
    }

    /**
     * 加载图片
     * @param url
     */
    @Override
    public void setImage(String url) {
        RequestOptions options = new RequestOptions();
        options.centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error);

        Glide.with(getContext())
                .load(url)
                .apply(options)
                .into(image_daily);
    }

    @Override
    public void setText(String en, String ch, String tts) {
        tv_daily_en.setText(en);
        tv_daily_ch.setText(ch);
        voiceUrl = tts;
    }

    @Override
    public void setDailyCount(int count) {
        tv_comp.setText("今日已完成数量: " + count);
    }

    @Override
    public void setDialyTarget(int target) {
        et_home_daylicount.setText(target + "");
    }

    @Override
    public void onRefresh() {
        mPresenter.loadDaliySentence(TimeUtils.getShortDate(date, 0l));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_home_alert:
                    mPresenter.writeTarget(Integer.valueOf(et_home_daylicount.getText().toString()));
                break;
            case R.id.ib_home_voice:
                playVolum();
                break;
            case R.id.btn_last:
                showProgress();
                mPresenter.loadDaliySentence(TimeUtils.getShortDate(date, TimeUtils.ONE_DAY));
                break;
        }
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * Rxmanager用于进程间通信, 用于设置监听使点击时图标改变事件
     */
    @Override
    public void autoSetVoiceIcon() {
        mRxMannager.addObserver(mRxMannager.toObserver(PlayComplete.class).subscribe(p -> {
            ib_home_voice.setImageResource(R.drawable.voice1);
        }));
    }

    @Override
    public void showSuccess(String str) {
        ToastUtils.showShort(str);
    }

    @Override
    public void showFail(String str) {
        ToastUtils.showShort(str);
    }
}
