package com.example.wero.a1_20154816395.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.ui.StateButton;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-19
 */

public class AnimationUtils {
    public static  void rightAnimation(View v1, View v2){
        YoYo.with(Techniques.ZoomOut).duration(700).playOn(v1);
        YoYo.with(Techniques.ZoomOut).duration(700).playOn(v2);

    }
    public static  void falseBAnimation(View v1, View v2){
        YoYo.with(Techniques.Tada).duration(400).playOn(v1);
        YoYo.with(Techniques.Tada).duration(400).playOn(v2);
//
//        ((StateButton)v1).setNormalBackgroundColor(ProApplication.getAppContext().getResources().getColor(R.color.black_less));
//        ((StateButton)v1).setNormalStrokeColor(ProApplication.getAppContext().getResources().getColor(R.color.right));
//
//
//        ((StateButton)v2).setNormalBackgroundColor(ProApplication.getAppContext().getResources().getColor(R.color.black_less));
//        ((StateButton)v2).setNormalStrokeColor(ProApplication.getAppContext().getResources().getColor(R.color.right));
//        v1.setEnabled(true);
//        v1.setEnabled(true);
    }

    public static void slideAnimation(View v1, View v2){
        YoYo.with(Techniques.SlideOutLeft)
                .duration(400)
                .playOn(v1);
        YoYo.with(Techniques.SlideInRight)
                .duration(400)
                .playOn(v2);
        v2.setVisibility(View.VISIBLE);
    }

    public static void fadeAnimation(View v1, View v2){
        YoYo.with(Techniques.FadeOut)
                .duration(50)
                .playOn(v1);
        YoYo.with(Techniques.FadeIn)
                .duration(400)
                .playOn(v2);
        v2.setVisibility(View.VISIBLE);
    }
}
