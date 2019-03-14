package com.lishide.anddevmvp.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.ArmsUtils;
import com.lishide.anddevmvp.BuildConfig;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

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
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        //leakCanary内存泄露检查
        ArmsUtils.obtainAppComponentFromContext(application).extras().put(RefWatcher.class.getName(),
                BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
