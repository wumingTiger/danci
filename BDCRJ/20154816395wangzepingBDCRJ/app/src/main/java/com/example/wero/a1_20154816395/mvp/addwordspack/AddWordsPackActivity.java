package com.example.wero.a1_20154816395.mvp.addwordspack;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.adapter.AddWordsAdapter;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.bean.WordPackBean;
import com.example.wero.a1_20154816395.mvp.MvpActivity;
import com.github.clans.fab.FloatingActionButton;
import com.xw.repo.XEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-19
 */

public class AddWordsPackActivity extends MvpActivity<AddWordsPackPresenter> implements AddWordsPackContract.View {
    @BindView(R.id.toolbar_addwordspack) Toolbar toolbar;
    @BindView(R.id.rcv_addwordspack) RecyclerView mRecyclerView;
    @BindView(R.id.fab_addword) FloatingActionButton mFloatingActionButton;
    @BindView(R.id.et_addwordspack_colname) XEditText mXEditText;
    @BindView(R.id.tv_addword_head) TextView tv_addword_head;

    AddWordsAdapter mAddWordsAdapter;
    List<WordBean> mWordBeanList;
    LinearLayoutManager mLinearLayoutManager;
    int count = 1;

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

        //初始化rcv
        mWordBeanList = new ArrayList<>();
        //初始为1
        mWordBeanList.add(new WordBean("", "", false));
        mLinearLayoutManager = new LinearLayoutManager(this);
        mAddWordsAdapter = new AddWordsAdapter(mWordBeanList);
        //这里设置了最大卡组,过高会导致oom
        mRecyclerView.setItemViewCacheSize(100);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAddWordsAdapter);

        //添加监听事件
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                mWordBeanList.add(new WordBean("", "", false));
                //我心里好苦啊
                mAddWordsAdapter.notifyItemInserted(mWordBeanList.size()-1);
                count++;
                setHead(0, count);
            }
        });
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
    protected AddWordsPackPresenter createPresenter() {
        return new AddWordsPackPresenter(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_addwordspack;
    }

    @Override
    public void setHead(int acc, int count) {
        tv_addword_head.setText(count + "");
    }

    @Override
    public void closeAct() {
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_right, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tb_right) {
            if (!mXEditText.getText().toString().trim().equals("")){
                mPresenter.addWordPack(generateData());
            }else {
                ToastUtils.showShort("词组名不能为空");
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 获取卡组里面的数据
     * @return
     */
    private WordPackBean generateData(){
        WordPackBean w = new WordPackBean(mXEditText.getText().toString().trim());
        w.setUserid(ProApplication.USER.getId());
        List<WordBean> list = new ArrayList<>();
        for (WordBean wordBean : mWordBeanList){
            if (!wordBean.getEn().equals(""))
                list.add(wordBean);
        }
        w.setWords(list);
        w.setCount(Long.valueOf(list.size()));
        return w;
    }
}
