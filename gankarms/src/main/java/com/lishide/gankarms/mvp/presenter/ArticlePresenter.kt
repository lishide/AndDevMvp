package com.lishide.gankarms.mvp.presenter

import android.app.Application
import android.support.v7.widget.RecyclerView
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.lishide.gankarms.mvp.contract.ArticleContract
import com.lishide.gankarms.mvp.model.entity.GankEntity
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@FragmentScope
class ArticlePresenter @Inject
constructor(model: ArticleContract.Model,
            rootView: ArticleContract.View,
            private var mList: ArrayList<GankEntity>?,
            private var mAdapter: RecyclerView.Adapter<*>?,
            private var mErrorHandler: RxErrorHandler?,
            private var mApplication: Application?,
            private var mImageLoader: ImageLoader?,
            private var mAppManager: AppManager?
) : BasePresenter<ArticleContract.Model, ArticleContract.View>(model, rootView) {

    override fun onDestroy() {
        super.onDestroy()
        this.mList = null
        this.mAdapter = null
        this.mErrorHandler = null
        this.mAppManager = null
        this.mImageLoader = null
        this.mApplication = null
    }

    fun requestData(pullToRefresh: Boolean) {
        if (pullToRefresh) {
            mRootView.showLoading()//显示下拉刷新的进度条
        }
        mList?.clear()
        mList?.addAll(mModel.getEntity())
        mAdapter?.notifyDataSetChanged()
        mRootView.setEmpty(mList?.isEmpty() ?: false)

        if (pullToRefresh) {
            mRootView.hideLoading()//隐藏下拉刷新的进度条
        }
    }
}
