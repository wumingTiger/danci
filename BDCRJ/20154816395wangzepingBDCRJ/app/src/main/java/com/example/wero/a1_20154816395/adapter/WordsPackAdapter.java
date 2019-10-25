package com.example.wero.a1_20154816395.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.bean.WordPackBean;

import java.util.List;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-10
 */

public class WordsPackAdapter extends ArrayAdapter<WordPackBean> {
    private int resourceId;
    private List<WordPackBean> wordPackBeanList;
    public WordsPackAdapter(@NonNull Context context, int resource, @NonNull List<WordPackBean> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
        this.wordPackBeanList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.count = view.findViewById(R.id.tv_words_count);
            viewHolder.title = view.findViewById(R.id.tv_words_title);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (position == 0){
            viewHolder.title.setTextColor(ProApplication.getAppContext().getResources().getColor(R.color.cfore1));
        }
        viewHolder.title.setText(wordPackBeanList.get(position).getTopic());
        viewHolder.count.setText(String.valueOf(wordPackBeanList.get(position).getCount()) + "个单词");
        return view;
    }

    @Override
    public int getCount() {
        return wordPackBeanList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public WordPackBean getItem(int position) {
        return wordPackBeanList.get(position);
    }

    public List getList(){
        return wordPackBeanList;
    }

    class ViewHolder{
        TextView count;
        TextView title;
    }
}
