package com.example.wero.a1_20154816395.mvp.nomvpactivity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.base.BaseActivity;
import com.example.wero.a1_20154816395.base.BasePresenter;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.mvp.MvpActivity;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.utils.AnimationUtils;
import com.example.wero.a1_20154816395.utils.ConvertUtils;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-19
 */

public class MxActivity extends MvpActivity implements View.OnClickListener, TextWatcher{
    @BindView(R.id.toolbar_mx) Toolbar toolbar;
    @BindView(R.id.tv_mx_ch) TextView tv_mx_ch;
    @BindView(R.id.tv_mx_right) TextView tv_mx_right;
    @BindView(R.id.tv_mx_en) TextView tv_mx_en;
    @BindView(R.id.edittext_mx) EditText edittext_mx;
    @BindView(R.id.btn_mx) Button btn_mx;
    @BindView(R.id.tv_mx_wrong) TextView tv_mx_wrong;
    @BindView(R.id.mx_line1) LinearLayout mx_line1;
    @BindView(R.id.pgb_mx) NumberProgressBar pgb_mx;
    private List<WordBean> mWordBeans;
    private String currentEn;
    private String currentCh;
    private int currentIndex = 0;
    private int count;

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
        btn_mx.setOnClickListener(this);
        edittext_mx.addTextChangedListener(this);
        refreshView();
        pgb_mx.setProgress(ConvertUtils.convert(currentIndex, count));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_mx;
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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        tv_mx_right.setVisibility(View.VISIBLE);
        tv_mx_wrong.setVisibility(View.VISIBLE);
        tv_mx_en.setVisibility(View.VISIBLE);
    }

    private void refreshView(){
        if (currentIndex < count){
            WordBean wordBean = mWordBeans.get(currentIndex);
            currentCh = wordBean.getCh();
            currentEn = wordBean.getEn();
            tv_mx_ch.setText(wordBean.getCh());
            tv_mx_en.setText(wordBean.getEn());
            tv_mx_right.setVisibility(View.GONE);
            tv_mx_wrong.setVisibility(View.GONE);
            tv_mx_en.setVisibility(View.GONE);
            edittext_mx.setText("");
        }else {
            ToastUtils.showShort("完成默写");
            this.finish();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String en = edittext_mx.getText().toString();
        if (en.equals(currentEn)){
            delyRefresh();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void delyRefresh(){
        AnimationUtils.fadeAnimation(mx_line1, mx_line1);
        mRxMannager.addObserver(Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Thread.sleep(50);
                emitter.onNext(new Object());
            }
        }).compose(RxSchedulersHelper.io_main())
            .subscribe(o ->{
                currentIndex++;
                refreshView();
            }));
        pgb_mx.setProgress(ConvertUtils.convert(currentIndex, count));
    }
}
