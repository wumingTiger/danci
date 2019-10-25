package com.example.wero.a1_20154816395.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.rxutils.RxBus;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.service.AudioService;
import com.example.wero.a1_20154816395.utils.PlayComplete;
import com.example.wero.a1_20154816395.utils.StarWordHelper;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-17
 */

public class WordsListAdapter extends ArrayAdapter<WordBean> {
    private int resourceId;
    private List<WordBean> wordsList;
    private Activity mActivity;

    public WordsListAdapter(@NonNull Context context, int resource, @NonNull List<WordBean> objects, Activity activity) {
        super(context, resource, objects);
        this.resourceId = resource;
        this.wordsList = objects;
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        WordBean wordBean = getItem(position);
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_word_en = view.findViewById(R.id.tv_word_en);
            viewHolder.tv_word_ch = view.findViewById(R.id.tv_word_ch);
            viewHolder.ib_voice = view.findViewById(R.id.ib_voice);
            viewHolder.sb_star = view.findViewById(R.id.sb_star);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_word_en.setText(wordBean.getEn());
        viewHolder.tv_word_ch.setText(wordBean.getCh());
        viewHolder.sb_star.setChecked(wordBean.isStar());

        viewHolder.sb_star.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                if (checked){
                    StarWordHelper.addStar(wordBean.getEn());
                    LogUtils.e(wordBean.hashCode());
                    wordBean.setStar(true);
                }else {
                    StarWordHelper.cancleStar(wordBean.getEn());
                    wordBean.setStar(false);
                }
            }
        });

        viewHolder.ib_voice.setOnClickListener(v -> {
            viewHolder.ib_voice.setImageResource(R.drawable.voice2);
            AudioService.startVoiceByService(mActivity, AudioService.WORD_URL + wordBean.getEn());
        });
        RxBus.getInstance().toObserver(PlayComplete.class)
                .compose(RxSchedulersHelper.io_main())
                .subscribe(p -> {
                    viewHolder.ib_voice.setImageResource(R.drawable.voice1);
                });
        return view;
    }

    @Override
    public int getCount() {
        return wordsList.size();
    }

    @Nullable
    @Override
    public WordBean getItem(int position) {
        return wordsList.get(position);
    }
    public List getList(){
        return wordsList;
    }
    class ViewHolder{
        public TextView tv_word_en;
        public TextView tv_word_ch;
        public ImageButton ib_voice;
        public ShineButton sb_star;
    }
}
