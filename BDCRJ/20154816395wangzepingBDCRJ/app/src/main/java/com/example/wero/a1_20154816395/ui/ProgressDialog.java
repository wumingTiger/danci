package com.example.wero.a1_20154816395.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.wero.a1_20154816395.R;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-18
 */

public class ProgressDialog extends Dialog {
    @BindView(R.id.avl_pacaman) AVLoadingIndicatorView mAVLoadingIndicatorView;

    public ProgressDialog(@NonNull Context context) {
        super(context);
    }

    public ProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressdiog);
        ButterKnife.bind(this);
    }
}
