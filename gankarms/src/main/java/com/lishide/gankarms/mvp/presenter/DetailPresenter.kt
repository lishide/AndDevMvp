package com.lishide.gankarms.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.lishide.gankarms.mvp.contract.DetailContract
import com.lishide.gankarms.mvp.model.entity.GankEntity
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

@ActivityScope
class DetailPresenter @Inject
constructor(model: DetailContract.Model,
            rootView: DetailContract.View,
            private var mErrorHandler: RxErrorHandler?,
            private var mApplication: Application?,
            private var mImageLoader: ImageLoader?,
            private var mAppManager: AppManager?
) : BasePresenter<DetailContract.Model, DetailContract.View>(model, rootView) {

    override fun onDestroy() {
        super.onDestroy()
        this.mErrorHandler = null
        this.mAppManager = null
        this.mImageLoader = null
        this.mApplication = null
    }

    fun getQuery(id: String) {
        mRootView.onLikeChange(mModel.queryById(id).isNotEmpty())
    }

    fun removeById(id: String) {
        mModel.removeById(id)
        mRootView.onLikeChange(false)
    }

    fun addToLike(entity: GankEntity) {
        mModel.addToLike(entity)
        mRootView.onLikeChange(true)
    }
}
