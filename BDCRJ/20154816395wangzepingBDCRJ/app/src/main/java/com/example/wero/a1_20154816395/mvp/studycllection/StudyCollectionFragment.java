package com.example.wero.a1_20154816395.mvp.studycllection;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.adapter.WordsPackAdapter;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.bean.WordPackBean;
import com.example.wero.a1_20154816395.mvp.MvpFragment;
import com.example.wero.a1_20154816395.mvp.words.WordsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-10
 */

public class StudyCollectionFragment extends MvpFragment<StudyCollectionPresenter> implements StudyCollectionContract.View, SwipeRefreshLayout.OnRefreshListener
                                                                            ,AdapterView.OnItemClickListener{
    @BindView(R.id.lv_studycollection) ListView listView;
    @BindView(R.id.srefresh_studycollection) SwipeRefreshLayout mSwipeRefreshLayout;
    WordsPackAdapter mWordsPackAdapter;
    private List<WordPackBean> mWordPackBeanList = new ArrayList<>();

    @Override
    protected StudyCollectionPresenter createPresenter() {
        return new StudyCollectionPresenter(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_studycollection;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.cfore2, R.color.cfore1);
        mWordsPackAdapter = new WordsPackAdapter(getContext(), R.layout.wordpackage, mWordPackBeanList);
        listView.setAdapter(mWordsPackAdapter);
        listView.setOnItemClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.loadWordPack();
    }

    @Override
    public void showWordPack(List<WordPackBean> wordPackBeans) {
        mWordPackBeanList.clear();
        mWordPackBeanList.addAll(wordPackBeans);
        mWordsPackAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSuccess(String str) {
        ToastUtils.showShort(str);
    }

    @Override
    public void showFail(String str) {
        ToastUtils.showShort(str);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadWordPack();
    }

    @Override
    public void onResume() {
        mPresenter.loadWordPack();
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("words", mWordPackBeanList.get(position));
        intent.setClass(getContext(), WordsActivity.class);
        startActivity(intent);
    }
}
