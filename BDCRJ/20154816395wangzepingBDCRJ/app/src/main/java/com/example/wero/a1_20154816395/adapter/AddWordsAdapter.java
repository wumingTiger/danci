package com.example.wero.a1_20154816395.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.xw.repo.XEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-19
 */

public class AddWordsAdapter extends RecyclerView.Adapter<AddWordsAdapter.ViewHolder>{

    List<WordBean> mWordBeanList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addwordspack, parent, false);
        return new AddWordsAdapter.ViewHolder(view);
    }

    public AddWordsAdapter(List<WordBean> mWordBeanList) {
        this.mWordBeanList = mWordBeanList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final WordBean wordBean = mWordBeanList.get(position);
//        if (holder.et_word.getTag() instanceof TextWatcher){
//            holder.et_word.removeTextChangedListener((TextWatcher) holder.et_word.getTag());
//        }
//        if (holder.et_define.getTag() instanceof TextWatcher){
//            holder.et_define.removeTextChangedListener((TextWatcher) holder.et_define.getTag());
//        }
        holder.et_word.setText(wordBean.getEn());
        holder.et_define.setText(wordBean.getCh());
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wordBean.setEn(holder.et_word.getText().toString());
                wordBean.setCh(holder.et_define.getText().toString());
                LogUtils.e(mWordBeanList.get(0).getEn());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        holder.et_word.addTextChangedListener(textWatcher);
        holder.et_define.addTextChangedListener(textWatcher);
        LogUtils.e(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return mWordBeanList.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        XEditText et_word;
        XEditText et_define;
        TextView tv_num;
        public ViewHolder(View view) {
            super(view);
            tv_num = (TextView) view.findViewById(R.id.tv_mnum);
            et_word = (XEditText) view.findViewById(R.id.et_addwords_word);
            et_define = (XEditText) view.findViewById(R.id.et_addwords_define);
        }
    }
    public List<WordBean> getmWordBeanList() {
        return mWordBeanList;
    }

}
