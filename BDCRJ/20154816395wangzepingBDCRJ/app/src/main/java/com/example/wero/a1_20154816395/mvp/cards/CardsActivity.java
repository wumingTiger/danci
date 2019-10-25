package com.example.wero.a1_20154816395.mvp.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.adapter.CardAdapter;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.bean.WordPackBean;
import com.example.wero.a1_20154816395.mvp.MvpActivity;
import com.example.wero.a1_20154816395.mvp.nomvpactivity.QueryWordActivity;
import com.example.wero.a1_20154816395.utils.ConvertUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-18
 */

public class CardsActivity extends MvpActivity<CardsPresenter> implements CardsContract.View{
    @BindView(R.id.rcv_cards) RecyclerView mRecyclerView;
    @BindView(R.id.pgb_cards) NumberProgressBar mNumberProgressBar;
    @BindView(R.id.toolbar_cards) Toolbar toolbar;
     //头部总数
    float allItems;
    //当前位置
    float adapterNowPos = 1;
    private List<WordBean> mWordBeanList;
    private CardAdapter mCardAdapter;

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

        mWordBeanList = (List<WordBean>) getIntent().getSerializableExtra("words");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        allItems = mWordBeanList.size();
        mNumberProgressBar.setProgress(ConvertUtils.convert(adapterNowPos, allItems));
        mCardAdapter = new CardAdapter(mWordBeanList, this);
        mRecyclerView.setAdapter(mCardAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView,dx,dy);
                adapterNowPos = gridLayoutManager.findFirstVisibleItemPosition();
                setHead(adapterNowPos, allItems);
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_cards;
    }

    @Override
    protected CardsPresenter createPresenter() {
        return new CardsPresenter(this);
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
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
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public void setHead(float x, float y) {
        //设置进度条
        mNumberProgressBar.setProgress(ConvertUtils.convert(adapterNowPos, allItems));
    }
}
