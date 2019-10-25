package com.example.wero.a1_20154816395.mvp.nomvpactivity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.api.WordApi;
import com.example.wero.a1_20154816395.base.BaseActivity;
import com.example.wero.a1_20154816395.base.BasePresenter;
import com.example.wero.a1_20154816395.bean.QueryWordBean;
import com.example.wero.a1_20154816395.mvp.MvpActivity;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.service.AudioService;
import com.example.wero.a1_20154816395.utils.PlayComplete;
import com.example.wero.a1_20154816395.utils.RetrofitHelper;
import com.example.wero.a1_20154816395.utils.StarWordHelper;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.xw.repo.XEditText;
import java.util.List;
import butterknife.BindView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-21
 */

public class QueryWordActivity extends MvpActivity implements View.OnClickListener, ShineButton.OnCheckedChangeListener{
    @BindView(R.id.toolbar_query) Toolbar toolbar;
    @BindView(R.id.tv_queryword_word) TextView tv_queryword_word;
    @BindView(R.id.tv_queryword_pham) TextView tv_queryword_pham;
    @BindView(R.id.tv_queryword_phen) TextView tv_queryword_phen;
    @BindView(R.id.tv_queryword_def) TextView tv_queryword_def;
    @BindView(R.id.tv_queryword_items) TextView tv_queryword_items;
    @BindView(R.id.sb_queryword_star) ShineButton sb_queryword_star;
    @BindView(R.id.ib_queryword_voice) ImageButton ib_queryword_voice;
    @BindView(R.id.et_queryword) XEditText et_queryword;
    private String voiceUrl;
    private String currentWord;
    private boolean first = true;

    @Override
    protected void initView() {
        ib_queryword_voice.setOnClickListener(this);
        sb_queryword_star.setOnCheckStateChangeListener(this);
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
        autoSetVoiceIcon();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    /**
     * 查询单词, retrofit
     * @param w
     */
    public void queryWord(String w){
        RetrofitHelper.getWordApi().getWord(w, "json", WordApi.KEY)
                .compose(RxSchedulersHelper.io_main())
                .subscribe(q -> {
                    setDisplay(q);
                }, e -> {
                    //词霸服务器返回的数据格式不一致
                    LogUtils.e(e.getMessage());
                });
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_queryword;
    }

    @Override
    public void onClick(View v) {
        playVolum();
    }


    /**
     * 点击播放
     */
    public void playVolum(){
        if (voiceUrl == null || voiceUrl.equals("")){
            ToastUtils.showShort("播放错误");
            return;
        }
        ib_queryword_voice.setImageResource(R.drawable.voice2);
        AudioService.startVoiceByService(this, voiceUrl);
    }

    /**
     * 显示数据
     * @param q
     */
    private void setDisplay(QueryWordBean q){
        if (first){
            tv_queryword_word.setVisibility(View.VISIBLE);
            sb_queryword_star.setVisibility(View.VISIBLE);
            ib_queryword_voice.setVisibility(View.VISIBLE);
            tv_queryword_def.setVisibility(View.INVISIBLE);
            first = false;
        }
        if (q.getSymbols() != null){
            QueryWordBean.SymbolsBean symbol = q.getSymbols().get(0);
            currentWord = q.getWord_name();
            sb_queryword_star.setChecked(StarWordHelper.checkStar(currentWord));
            tv_queryword_word.setText(currentWord);
            tv_queryword_pham.setText("美[" + symbol.getPh_am() + "]");
            tv_queryword_phen.setText("英[" + symbol.getPh_en() + "]");
            voiceUrl = symbol.getPh_am_mp3();
            String s = "";
            List<QueryWordBean.SymbolsBean.PartsBean> parts = symbol.getParts();
            if (parts != null){
                for (QueryWordBean.SymbolsBean.PartsBean p : parts){
                    s += p.getPart() + "\n" + p.getMeans() + "\n\n";
                }
            }else {
                s = "该单词不存在";
            }
            tv_queryword_items.setText(s);
        }else {
            ToastUtils.showShort("数据出差,请重试!");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tb_query) {
            queryWord(et_queryword.getText().toString().trim());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void autoSetVoiceIcon() {
        mRxMannager.addObserver(mRxMannager.toObserver(PlayComplete.class).subscribe(p -> {
            ib_queryword_voice.setImageResource(R.drawable.voice1);
        }));
    }

    @Override
    public void onCheckedChanged(View view, boolean checked) {
        if (checked){
            mRxMannager.addObserver(StarWordHelper.addStar(currentWord));
        }else {
            mRxMannager.addObserver(StarWordHelper.cancleStar(currentWord));
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
