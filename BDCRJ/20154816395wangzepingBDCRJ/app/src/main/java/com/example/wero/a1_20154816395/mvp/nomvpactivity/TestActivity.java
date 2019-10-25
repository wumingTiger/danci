package com.example.wero.a1_20154816395.mvp.nomvpactivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.base.BaseActivity;
import com.example.wero.a1_20154816395.base.BasePresenter;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.mvp.MvpActivity;
import com.example.wero.a1_20154816395.ui.ErrorDialog;
import com.example.wero.a1_20154816395.ui.StateButton;
import com.example.wero.a1_20154816395.utils.AnimationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;

import butterknife.BindView;
import butterknife.BindViews;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-19
 * 自定义button的使用
 */

public class TestActivity extends MvpActivity implements View.OnClickListener{
    @BindView(R.id.toolbar_test) Toolbar toolbar;
    @BindViews({R.id.btn_1, R.id.btn_2, R.id.btn_3,
            R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
            R.id.btn_8, R.id.btn_9, R.id.btn_10, R.id.btn_11,
            R.id.btn_12}) List<StateButton> mStateButtonList;

    private List<WordBean> mWordBeans;
    List<StateButton> mStateRandomButtonList;
    Map<String, Object> mMap;
    ErrorDialog errorDialog;
    int remain = 12;
    int lastIndex = -1;
    Timer timer;

    @Override
    protected void initView() {
        mMap = new HashMap<>();
        setSupportActionBar(toolbar);
        mWordBeans = new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        for (StateButton sb: mStateButtonList) {
            sb.setOnClickListener(this);
        }
        timer = new Timer();
        setRandom();
        setmWordBeansAndMap((List<WordBean>) getIntent().getSerializableExtra("words"));
        errorDialog = new ErrorDialog(this);
        errorDialog.setOnDismissListener( e ->{
            this.finish();
        });
    }

    private void setRandom(){
        Set<StateButton> set = new HashSet<>();
        while (set.size() < 12){
            set.add(mStateButtonList.get(new Random().nextInt(12)));
        }
        mStateRandomButtonList = new ArrayList<>();
        mStateRandomButtonList.addAll(set);
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_test;
    }

    //监听点击动作,实现动画
    @Override
    public void onClick(View v) {
        setButtonBackGround(1, (StateButton)v);
        if (lastIndex == -1){
            lastIndex = mStateButtonList.indexOf(v);
        }else {
            StateButton stateButton = (StateButton)v;
            if (mStateButtonList.get(lastIndex).getNum() == stateButton.getNum()){
                AnimationUtils.rightAnimation(v, mStateButtonList.get(lastIndex));
                lastIndex = -1;
                remain -= 2;
                if (remain == 0){
                    showDialog();
                }
            }
            else {
                setButtonBackGround(2, stateButton);
                setButtonBackGround(2, mStateButtonList.get(lastIndex));
                AnimationUtils.falseBAnimation(v, mStateButtonList.get(lastIndex));
                alertBackGroundOnThread(v);
            }
        }

    }

    private void showDialog() {
        errorDialog.show();
        errorDialog.setDialog_ch2("");
        errorDialog.setDialog_en("");
        errorDialog.setDialog_ch("再接再厉");
        errorDialog.dialog_right.setText("");
        errorDialog.dialog_head.setText("测试完成");
        errorDialog.dialog_head.setBackgroundColor(getResources().getColor(R.color.right));
        Drawable drawable = getResources().getDrawable(R.drawable.happy);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        errorDialog.dialog_head.setCompoundDrawables(drawable, null, null, null);
    }

    private void setButtonBackGround(int setorcancle, StateButton stateButton){
        if(setorcancle == 1){
            stateButton.setUnableStrokeColor(getResources().getColor(R.color.lightgreen));
            stateButton.setUnableBackgroundColor(getResources().getColor(R.color.cfore1));
            //与xml大小不一致
            stateButton.setUnableStrokeWidth(15);
            stateButton.setEnabled(false);
        }else if(setorcancle == 0){
            stateButton.setNormalStrokeColor(getResources().getColor(R.color.cfore2));
            stateButton.setNormalBackgroundColor(getResources().getColor(R.color.black_less));
            stateButton.setNormalStrokeWidth(10);
        }else if(setorcancle == 2){
            stateButton.setEnabled(true);
            stateButton.setNormalBackgroundColor(getResources().getColor(R.color.wrong));
        }
    }

    /**
     * io线程休眠,主线程修改ui
     * @param v
     */
    private void alertBackGroundOnThread(View v){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                LogUtils.e("sleep");
                Thread.sleep(300);
                emitter.onNext(new Object());
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Exception {
                    setButtonBackGround(0, (StateButton)v);
                    setButtonBackGround(0, mStateButtonList.get(lastIndex));
                    lastIndex = -1;
                }
            });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tb_query) {
            Intent intent = new Intent();
            intent.setClass(this, QueryWordActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置随机,
     * 这里做到了双层随机,
     * button随机, 单词随机
     * @param mWordBeans
     */
    public void setmWordBeansAndMap(List<WordBean> mWordBeans) {
        if (mWordBeans.size() >= 6){
            Collections.shuffle(mWordBeans);
            for (int i=0; i<6; i++){
                this.mWordBeans.add(mWordBeans.get(i));
                mStateRandomButtonList.get(i).setText(mWordBeans.get(i).getCh());
                mStateRandomButtonList.get(i+6).setText(mWordBeans.get(i).getEn());
                //设置标记
                mStateRandomButtonList.get(i).setNum(i);
                mStateRandomButtonList.get(i+6).setNum(i);
            }
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
