package com.example.wero.a1_20154816395.mvp.words;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.adapter.WordsListAdapter;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.bean.WordPackBean;
import com.example.wero.a1_20154816395.mvp.MvpActivity;
import com.example.wero.a1_20154816395.mvp.alterwordspack.AlterWordsPackActivity;
import com.example.wero.a1_20154816395.mvp.cards.CardsActivity;
import com.example.wero.a1_20154816395.mvp.listen.ListenActivity;
import com.example.wero.a1_20154816395.mvp.nomvpactivity.MxActivity;
import com.example.wero.a1_20154816395.mvp.nomvpactivity.TestActivity;
import com.example.wero.a1_20154816395.mvp.study.StudyActivity;
import com.example.wero.a1_20154816395.ui.MyListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-17
 */

public class WordsActivity extends MvpActivity<WordsPresenter> implements WordsContract.View, View.OnClickListener{
    @BindView(R.id.tv_head_count) TextView tv_head_count;
    @BindView(R.id.tv_head_title) TextView tv_head_title;
    @BindView(R.id.toolbar_words) Toolbar toolbar;
    @BindView(R.id.lv_wordsgroup) MyListView mListView;
    @BindView(R.id.btn_study) Button btn_study;
    @BindView(R.id.btn_cards) Button btn_cards;
    @BindView(R.id.btn_handwriting) Button btn_handwriting;
    @BindView(R.id.btn_listen) Button btn_listen;
    @BindView(R.id.btn_test) Button btn_test;
    WordsListAdapter mWordsListAdapter;
    WordPackBean mWordPackBean;
    List<WordBean> mWordBeanList = new ArrayList<>();

    @Override
    protected WordsPresenter createPresenter() {
        return new WordsPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mWordPackBean = (WordPackBean) getIntent().getSerializableExtra("words");
        mWordBeanList = mWordPackBean.getWords();
        mWordsListAdapter = new WordsListAdapter(WordsActivity.this, R.layout.word2list, mWordBeanList, this);
        mListView.setAdapter(mWordsListAdapter);
        btn_study.setOnClickListener(this);
        btn_cards.setOnClickListener(this);
        btn_handwriting.setOnClickListener(this);
        btn_listen.setOnClickListener(this);
        btn_test.setOnClickListener(this);
        tv_head_count.setText(mWordPackBean.getCount() + "个词语");
        tv_head_title.setText(mWordPackBean.getTopic());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_words;
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
    public void onClick(View v) {
        Intent intent = new Intent();
        if(mWordPackBean.getCount() == 0){
            return;
        }
        intent.putExtra("words", (Serializable) mWordPackBean.getWords());
        switch (v.getId()){
            case R.id.btn_study:
                intent.setClass(WordsActivity.this, StudyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_cards:
                intent.setClass(WordsActivity.this, CardsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_handwriting:
                intent.setClass(WordsActivity.this, MxActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_listen:
                intent.setClass(WordsActivity.this, ListenActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_test:
                if (mWordPackBean.getCount() >= 6){
                    intent.setClass(WordsActivity.this, TestActivity.class);
                    startActivity(intent);
                }
                else
                    ToastUtils.showShort("该组单词小于6个,不能测试!");
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tb_alter) {
            Intent intent = new Intent();
            intent.putExtra("pack", mWordPackBean);
            intent.setClass(WordsActivity.this, AlterWordsPackActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alter, menu);
        return true;
    }
}
