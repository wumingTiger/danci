package com.example.wero.a1_20154816395.mvp.alterwordspack;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.blankj.utilcode.util.ToastUtils;
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
 * @date: 18-5-28
 */

public class AlterWordsPackActivity extends MvpActivity<AlterWordsPackPresenter> implements AlterWordsPackContract.View {
    @BindView(R.id.toolbar_addwordspack) Toolbar toolbar;
    @BindView(R.id.rcv_addwordspack) RecyclerView mRecyclerView;
    @BindView(R.id.fab_addword) FloatingActionButton mFloatingActionButton;
    @BindView(R.id.et_addwordspack_colname) XEditText mXEditText;
    @BindView(R.id.tv_addword_head) TextView tv_addword_head;
    @BindView(R.id.tv_addword_addoralter) TextView tv_addword_addoralter;
    @BindView(R.id.tv_addwords_headname) TextView tv_addwords_headname;
    WordPackBean mWordPackBean;
    AddWordsAdapter mAddWordsAdapter;
    List<WordBean> mWordBeanList;
    LinearLayoutManager mLinearLayoutManager;
    int count;
    @Override
    protected AlterWordsPackPresenter createPresenter() {
        return new AlterWordsPackPresenter(this);
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
        tv_addwords_headname.setText("修改学习集");
        tv_addword_addoralter.setText("添加或者修改单词");
        mWordPackBean = (WordPackBean) getIntent().getSerializableExtra("pack");
        mWordBeanList = mWordPackBean.getWords();
        count = (int) mWordPackBean.getCount();
        mXEditText.setText(mWordPackBean.getTopic());
        mXEditText.setEnabled(false);
        setHead(0, count);
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
    protected int getLayoutResource() {
        return R.layout.activity_addwordspack;
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
    public void setHead(int acc, int count) {
        tv_addword_head.setText(count + "");
    }

    @Override
    public void closeAct() {
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tb_right) {
            if (!mXEditText.getText().toString().trim().equals("")){
                mPresenter.alterWordPack(generateData());
            }else {
                ToastUtils.showShort("词组名不能为空");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_right, menu);
        return true;
    }


    /**
     * 获取卡组里面的数据
     * @return
     */
    private WordPackBean generateData(){
        mWordPackBean.setTopic(mXEditText.getText().toString().trim());
        List<WordBean> list = new ArrayList<>();
        for (WordBean wordBean : mWordBeanList){
            if (!wordBean.getEn().equals(""))
                list.add(wordBean);
        }
        mWordPackBean.setWords(list);
        mWordPackBean.setCount(Long.valueOf(list.size()));
        return mWordPackBean;
    }
}
