package com.example.wero.a1_20154816395.mvp.study;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.mvp.MvpActivity;
import com.example.wero.a1_20154816395.mvp.nomvpactivity.QueryWordActivity;
import com.example.wero.a1_20154816395.rxutils.RxBus;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.ui.ErrorDialog;
import com.example.wero.a1_20154816395.utils.AnimationUtils;
import com.example.wero.a1_20154816395.utils.ConvertUtils;
import com.example.wero.a1_20154816395.utils.DateConvertComp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import butterknife.BindView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-18
 * 这理不需要联网,可以不用mvp架构
 */

public class StudyActivity extends MvpActivity<StudyPresenter> implements StudyContract.View, View.OnClickListener, DialogInterface.OnDismissListener{

    enum Statu{FRONT, BACK}
    @BindView(R.id.tv_study_en) TextView tv_study_en;
    @BindView(R.id.tv_study_en_2) TextView tv_study_en_2;
    @BindView(R.id.pgb_study) NumberProgressBar mNumberProgressBar;
    @BindView(R.id.btn_study1) Button btn_study1;
    @BindView(R.id.btn_study2) Button btn_study2;
    @BindView(R.id.btn_study3) Button btn_study3;
    @BindView(R.id.btn_study4) Button btn_study4;
    @BindView(R.id.btn_study1_2) Button btn_study1_2;
    @BindView(R.id.btn_study2_2) Button btn_study2_2;
    @BindView(R.id.btn_study3_2) Button btn_study3_2;
    @BindView(R.id.btn_study4_2) Button btn_study4_2;
    @BindView(R.id.toolbar_study) Toolbar toolbar;
    @BindView(R.id.linear_study_1) LinearLayout linear_study_1;
    @BindView(R.id.linear_study_2) LinearLayout linear_study_2;

    private ErrorDialog mErrorDialog;
    private List<WordBean> mWordBeans;
    private Set<WordBean> mSet;
    private int count;
    private int currentIndex = 0;
    private Button btns_front[];
    private Button btns_back[];
    private String currentCh;
    private String currentEn;
    private Statu nextStatu = Statu.BACK;


    @Override
    protected StudyPresenter createPresenter() {
        return new StudyPresenter(this);
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mWordBeans = (List<WordBean>) getIntent().getSerializableExtra("words");
        count = mWordBeans.size();
        mErrorDialog = new ErrorDialog(this);
        btns_front = new Button[] {btn_study1, btn_study2, btn_study3, btn_study4};
        btns_back = new Button[] {btn_study1_2, btn_study2_2, btn_study3_2, btn_study4_2};
        for (Button b : btns_front){
            b.setOnClickListener(this);
        }
        for (Button b : btns_back){
            b.setOnClickListener(this);
        }
        mErrorDialog.setOnDismissListener(this);
        setData(Statu.FRONT);
    }

    /**
     * 设置button的数据
     * @param statu
     */
    private void setData(Statu statu) {
        ProApplication.EVERYDAY_COUNT++;
        if (count >= 4){
            //set的使用,这里有个坑
            mSet = new HashSet<>();
            Random random = new Random();
            mSet.add(mWordBeans.get(currentIndex));
            while (mSet.size() < 4){
                mSet.add(mWordBeans.get(random.nextInt(count)));
            }
            List<WordBean> list = new ArrayList<>(mSet);
            //打乱顺序
            Collections.shuffle(list);
            if (statu == Statu.FRONT){
                tv_study_en.setText(mWordBeans.get(currentIndex).getEn());
                for (int i = 0; i < 4; i++){
                    btns_front[i].setText(list.get(i).getCh());
                }
            }else {
                tv_study_en_2.setText(mWordBeans.get(currentIndex).getEn());
                for (int i = 0; i < 4; i++){
                    btns_back[i].setText(list.get(i).getCh());
                }
            }
        }else if (count > 0 && count <= 3){
            List<WordBean> list = new ArrayList<>(mWordBeans);
            //打乱顺序
            Collections.shuffle(list);
            if (statu == Statu.FRONT){
                for (int i = 0; i < count; i++){
                    btns_front[i].setText(list.get(i).getCh());
                }
            }else {
                for (int i = 0; i < count; i++){
                    btns_back[i].setText(list.get(i).getCh());
                }
            }
        }
        currentCh = mWordBeans.get(currentIndex).getCh();
        currentEn = mWordBeans.get(currentIndex).getEn();
        LogUtils.e(currentCh, currentEn);
        nextStatu = statu == Statu.FRONT ? Statu.BACK : Statu.FRONT;
    }

    /**
     * 判断,正确时启动动画
     * @param v
     */
    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if ((button.getText().equals(currentCh))){
            switch (v.getId()){
                case R.id.btn_study1:
                case R.id.btn_study2:
                case R.id.btn_study3:
                case R.id.btn_study4:
                    startAnim();
                    break;
                case R.id.btn_study1_2:
                case R.id.btn_study2_2:
                case R.id.btn_study3_2:
                case R.id.btn_study4_2:
                    startAnim();
                    break;
            }
        }else {
            showErrorDialog(button.getText().toString());
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_study;
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
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
     * 实现的dialog接口
     * @param dialog
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        startAnim();
    }

    /**
     * 开始更新界面和数据
     */
    private void startAnim(){
        if (currentIndex >= count-1){
            mPresenter.updateDailyCount();
            this.finish();
        }else {
            currentIndex++;
            if (nextStatu == Statu.FRONT){
                setData(Statu.FRONT);
                AnimationUtils.slideAnimation(linear_study_2, linear_study_1);
                mNumberProgressBar.setProgress(ConvertUtils.convert(currentIndex, count));
            }else {
                setData(Statu.BACK);
                AnimationUtils.slideAnimation(linear_study_1, linear_study_2);
                mNumberProgressBar.setProgress(ConvertUtils.convert(currentIndex, count));
            }
        }

    }

    @Override
    public void showErrorDialog(String s1) {
        mErrorDialog.show();
        mErrorDialog.setDialog_en(currentEn);
        mErrorDialog.setDialog_ch(currentCh);
        mErrorDialog.setDialog_ch2("你的答案:" + s1);
    }
}
