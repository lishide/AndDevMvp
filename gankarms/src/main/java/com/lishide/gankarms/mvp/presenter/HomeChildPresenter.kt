package com.lishide.gankarms.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.lishide.gankarms.mvp.contract.HomeChildContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@FragmentScope
class HomeChildPresenter @Inject
constructor(model: HomeChildContract.Model,
            rootView: HomeChildContract.View,
            private var mErrorHandler: RxErrorHandler?,
            private var mApplication: Application?,
            private var mImageLoader: ImageLoader?,
            private var mAppManager: AppManager?
) : BasePresenter<HomeChildContract.Model, HomeChildContract.View>(model, rootView) {

    override fun onDestroy() {
        super.onDestroy()
        this.mErrorHandler = null
        this.mAppManager = null
        this.mImageLoader = null
        this.mApplication = null
    }
}
