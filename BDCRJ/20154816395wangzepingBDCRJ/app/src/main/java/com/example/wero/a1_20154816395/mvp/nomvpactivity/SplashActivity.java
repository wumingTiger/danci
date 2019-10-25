package com.example.wero.a1_20154816395.mvp.nomvpactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.mvp.login.LoginActivity;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-28
 */

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 1500; // 两秒后进入系统

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPreferences = getSharedPreferences("isFirst", Context.MODE_PRIVATE);
        boolean b = sharedPreferences.getBoolean("isFirst", true);
        //判断是否是第一次启动app
        if (b){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirst", false);
            editor.commit();
        }
        //两秒后自动跳转
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent();
                if (b){
                    intent.setClass(SplashActivity.this, LaunchActivity.class);
                }else {
                    intent.setClass(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
