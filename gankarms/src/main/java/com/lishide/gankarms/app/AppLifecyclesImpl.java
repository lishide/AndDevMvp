package com.lishide.gankarms.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.ArmsUtils;
import com.lishide.gankarms.BuildConfig;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
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

    static {
        /* 全局指定 SmartRefreshLayout 样式 */
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) ->
                new MaterialHeader(context));
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) ->
                new ClassicsFooter(context).setDrawableSize(20));
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

        // initDB
        GreenDaoHelper.initDatabase(application);
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
