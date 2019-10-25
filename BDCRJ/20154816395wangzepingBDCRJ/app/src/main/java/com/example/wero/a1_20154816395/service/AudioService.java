package com.example.wero.a1_20154816395.service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.wero.a1_20154816395.rxutils.RxBus;
import com.example.wero.a1_20154816395.utils.PlayComplete;

import java.io.IOException;

/**
 *
 * 该服务用于语音的播放， 提供静态方法用于调用语音
 */

public class AudioService extends Service {

    public static void startVoiceByService(Activity activity, String w){
        Intent intent = new Intent(activity, AudioService.class);
        intent.putExtra("url", w);
        activity.startService(intent);
    }
    public static final String WORD_URL= "http://dict.youdao.com/dictvoice?audio=";
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        if (mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        String url = intent.getStringExtra("url");
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                // 释放资源
                try {
                    ToastUtils.showShort("播放错误");
                    mp.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        mMediaPlayer.setOnCompletionListener(mp -> {
            RxBus.getInstance().post(new PlayComplete());
        });
        mMediaPlayer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // 服务停止时停止播放音乐并释放资源
        mMediaPlayer.stop();
        mMediaPlayer.release();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
