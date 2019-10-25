package com.example.wero.a1_20154816395.mvp.nomvpactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.mvp.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

/**
 * luanch page
 */
public class LaunchActivity extends AppCompatActivity {
    private static final String TAG = LaunchActivity.class.getSimpleName();

    @BindView(R.id.banner_launch_background) public BGABanner mBackgroundBanner;
    @BindView(R.id.banner_launch_foreground) public BGABanner mForegroundBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        setListener();
        setImg();
    }

    /**
     * set skip Listener
     */
    private void setListener() {
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_launch_enter, R.id.tv_launch_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    /**
     * set display img
     */
    private void setImg() {
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        // set data source
        mBackgroundBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.drawable.launch1,
                R.drawable.launch2,
                R.drawable.launch3);

        //foreground
        List<View> views = new ArrayList<>();
        views.add(View.inflate(this, R.layout.launch_img_1, null));
        views.add(View.inflate(this, R.layout.launch_img_2, null));
        views.add(View.inflate(this, R.layout.launch_img_3, null));
        mForegroundBanner.setData(views);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }

}
