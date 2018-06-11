package com.lishide.gankarms.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.lishide.gankarms.mvp.contract.ArticleContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@FragmentScope
class ArticlePresenter @Inject
constructor(model: ArticleContract.Model,
            rootView: ArticleContract.View,
            private var mErrorHandler: RxErrorHandler?,
            private var mApplication: Application?,
            private var mImageLoader: ImageLoader?,
            private var mAppManager: AppManager?
) : BasePresenter<ArticleContract.Model, ArticleContract.View>(model, rootView) {

    override fun onDestroy() {
        super.onDestroy()
        this.mErrorHandler = null
        this.mAppManager = null
        this.mImageLoader = null
        this.mApplication = null
    }
}
