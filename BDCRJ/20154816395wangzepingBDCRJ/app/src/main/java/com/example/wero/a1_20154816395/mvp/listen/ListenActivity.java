package com.example.wero.a1_20154816395.mvp.listen;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.mvp.MvpActivity;
import com.example.wero.a1_20154816395.mvp.nomvpactivity.QueryWordActivity;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.service.AudioService;
import com.example.wero.a1_20154816395.utils.AnimationUtils;
import com.example.wero.a1_20154816395.utils.ConvertUtils;
import com.example.wero.a1_20154816395.utils.PlayComplete;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-20
 * 这里没用到mvp, 业务逻辑本身并不复杂
 */

public class ListenActivity extends MvpActivity<ListenPresenter> implements View.OnClickListener, ListenContract.View, TextWatcher {
    @BindView(R.id.toolbar_listen) Toolbar toolbar;
    @BindView(R.id.listen_line1) RelativeLayout listen_line1;
    @BindView(R.id.tv_listen_ch) TextView tv_listen_ch;
    @BindView(R.id.tv_listen_en) TextView tv_listen_en;
    @BindView(R.id.tv_listen_wrong) TextView tv_listeb_wrong;
    @BindView(R.id.pgb_listen) NumberProgressBar pgb_listen;
    @BindView(R.id.edittext_listen) EditText edittext_listen;
    @BindView(R.id.btn_listen) Button btn_listen;
    @BindView(R.id.ib_listen) ImageButton ib_listen;

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
        ib_listen.setOnClickListener(this);
        btn_listen.setOnClickListener(this);
        edittext_listen.addTextChangedListener(this);
        pgb_listen.setProgress(ConvertUtils.convert(currentIndex, count));
        autoSetVoiceIcon();
        refreshView();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_listen;
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
    protected ListenPresenter createPresenter() {
        return null;
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

    private void refreshView(){
        if (currentIndex < count){
            WordBean wordBean = mWordBeans.get(currentIndex);
            currentCh = wordBean.getCh();
            currentEn = wordBean.getEn();
            tv_listen_ch.setText(currentCh);
            tv_listen_en.setText(currentEn);
            tv_listen_ch.setVisibility(View.GONE);
            tv_listen_en.setVisibility(View.GONE);
            tv_listeb_wrong.setVisibility(View.GONE);
            edittext_listen.setText("");
            startPlayVoice();
        }else {
            ToastUtils.showShort("完成听写");
            this.finish();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String en = edittext_listen.getText().toString();
        if (en.equals(currentEn)){
            delyRefresh();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_listen){
            startPlayVoice();
        }else {
            tv_listeb_wrong.setVisibility(View.VISIBLE);
            tv_listen_en.setVisibility(View.VISIBLE);
            tv_listen_ch.setVisibility(View.VISIBLE);
        }
    }

    private void delyRefresh(){
        AnimationUtils.fadeAnimation(listen_line1, listen_line1);
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
        pgb_listen.setProgress(ConvertUtils.convert(currentIndex, count));
    }

    public void autoSetVoiceIcon() {
        mRxMannager.addObserver(mRxMannager.toObserver(PlayComplete.class).subscribe(p -> {
            setImgUnPlaying();
        }));
    }

    /**
     * 播放
     */
    private void startPlayVoice() {
        setImgPlaying();
        AudioService.startVoiceByService(this, AudioService.WORD_URL + currentEn);
    }
    @Override
    public void setImgPlaying() {
        ib_listen.setImageResource(R.drawable.voice2);
    }

    @Override
    public void setImgUnPlaying() {
        ib_listen.setImageResource(R.drawable.voice1);
    }
}
