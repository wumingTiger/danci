package com.example.wero.a1_20154816395.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.bean.WordBean;
import com.example.wero.a1_20154816395.rxutils.RxBus;
import com.example.wero.a1_20154816395.rxutils.RxSchedulersHelper;
import com.example.wero.a1_20154816395.service.AudioService;
import com.example.wero.a1_20154816395.utils.PlayComplete;
import com.example.wero.a1_20154816395.utils.StarWordHelper;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.List;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-18
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    List<WordBean> mWordBeanList;
    Activity mActivity;
    public CardAdapter(List<WordBean> list, Activity activity) {
        this.mWordBeanList = list;
        mActivity = activity;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        EasyFlipView easyFlipView;
        ImageButton ib_front;
        ShineButton sb_front;
        TextView tv_front;
        TextView tv_back;
        public ViewHolder(View view) {
            super(view);
            easyFlipView = (EasyFlipView)view.findViewById(R.id.item_flipview);
            ib_front = (ImageButton)view.findViewById(R.id.ib_cardfront_voice);
            sb_front = (ShineButton) view.findViewById(R.id.sb_cardfront_star);
            tv_back = (TextView) view.findViewById(R.id.tv_cards_ch);
            tv_front = (TextView) view.findViewById(R.id.tv_cards_en);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //ffffff,f
        WordBean wordBean = mWordBeanList.get(position);
        holder.easyFlipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.easyFlipView.setFlipDuration(700);
                holder.easyFlipView.flipTheView();
            }
        });
        holder.tv_front.setText(wordBean.getEn());
        holder.tv_back.setText(wordBean.getCh());
        holder.sb_front.setChecked(wordBean.isStar());
        holder.ib_front.setOnClickListener(v -> {
            holder.ib_front.setImageResource(R.drawable.voice2);
            AudioService.startVoiceByService(mActivity, AudioService.WORD_URL + wordBean.getEn());
        });
        holder.sb_front.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                if (checked){
                    StarWordHelper.addStar(wordBean.getEn());
                    wordBean.setStar(true);
                }else {
                    StarWordHelper.cancleStar(wordBean.getEn());
                    wordBean.setStar(false);
                }
            }
        });
        RxBus.getInstance().toObserver(PlayComplete.class)
                .compose(RxSchedulersHelper.io_main())
                .subscribe(p -> {
                    holder.ib_front.setImageResource(R.drawable.voice1);
                });
    }

    @Override
    public int getItemCount() {
        return mWordBeanList.size();
    }
}
