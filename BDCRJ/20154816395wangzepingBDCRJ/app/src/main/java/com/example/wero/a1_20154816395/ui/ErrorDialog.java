package com.example.wero.a1_20154816395.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wero.a1_20154816395.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-23
 */

public class ErrorDialog extends Dialog implements View.OnClickListener{
    @BindView(R.id.dialog_continue) public Button dialog_continue;
    @BindView(R.id.dialog_en)public TextView dialog_en;
    @BindView(R.id.dialog_ch)public TextView dialog_ch;
    @BindView(R.id.dialog_ch2)public TextView dialog_ch2;
    @BindView(R.id.dialog_head)public TextView dialog_head;
    @BindView(R.id.dialog_right)public TextView dialog_right;

    public ErrorDialog(@NonNull Context context) {
        super(context);
    }

    public ErrorDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.errordialog);
        ButterKnife.bind(this);
        dialog_continue.setOnClickListener(this);
    }

    public void setDialog_en(String dialog_en) {
        this.dialog_en.setText(dialog_en);
    }
    public void setDialog_ch(String dialog_ch) {
        this.dialog_ch.setText(dialog_ch);
    }
    public void setDialog_ch2(String dialog_ch2) {
        this.dialog_ch2.setText(dialog_ch2);
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }

}
