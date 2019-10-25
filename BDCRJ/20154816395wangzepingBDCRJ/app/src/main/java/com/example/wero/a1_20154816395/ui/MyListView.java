package com.example.wero.a1_20154816395.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-17
 */

public class MyListView extends ListView {
    public MyListView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
