package com.example.wero.a1_20154816395;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.example.wero.a1_20154816395.bean.UserBean;

/**
 * Created by wero on 18-4-24.
 * 初始化配置，操作
 */

public class ProApplication extends Application {
    public static Integer EVERYDAY_COUNT = 0;
    public static UserBean USER;
    private static Context mContext;
    private static ProApplication application;
    public static Integer DEF_TARGET = 60;

    public static boolean ISDEBUG;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        application = this;
        Utils.init(this);
    }

    /**
     * 全局的上下文
     * @return
     */
    public static Context getAppContext(){
        return mContext;
    }
}
