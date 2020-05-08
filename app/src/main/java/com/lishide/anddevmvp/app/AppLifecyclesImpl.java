package com.lishide.anddevmvp.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.jess.arms.base.delegate.AppLifecycles;

/**
 * 展示 {@link AppLifecycles} 的用法
 *
 * @author lishide
 * @date 2017/11/09
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {
//          MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(@NonNull Application application) {
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
