package com.lishide.gankarms.mvp.presenter

import android.app.Application
import android.support.v7.widget.RecyclerView
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import com.lishide.gankarms.mvp.contract.WelfareContract
import com.lishide.gankarms.mvp.model.entity.BaseResponse
import com.lishide.gankarms.mvp.model.entity.GankEntity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import javax.inject.Inject

@FragmentScope
class WelfarePresenter @Inject
constructor(model: WelfareContract.Model,
            rootView: WelfareContract.View,
            private var mList: ArrayList<GankEntity>?,
            private var mAdapter: RecyclerView.Adapter<*>?,
            private var mErrorHandler: RxErrorHandler?,
            private var mApplication: Application?,
            private var mImageLoader: ImageLoader?,
            private var mAppManager: AppManager?
) : BasePresenter<WelfareContract.Model, WelfareContract.View>(model, rootView) {

    override fun onDestroy() {
        super.onDestroy()
        this.mList = null
        this.mAdapter = null
        this.mErrorHandler = null
        this.mAppManager = null
        this.mImageLoader = null
        this.mApplication = null
    }

    private var preEndIndex: Int = 0

    fun requestData(pullToRefresh: Boolean) {

        mModel.getRandomGirl()
                .subscribeOn(Schedulers.io())
                .retryWhen(RetryWithDelay(3, 2))
                .doOnSubscribe {
                    if (pullToRefresh) {
                        mRootView.showLoading()//显示下拉刷新的进度条
                    } else {
                        mRootView.startLoadMore()//显示上拉加载更多的进度条
                    }
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    if (pullToRefresh) {
                        mRootView.hideLoading()//隐藏下拉刷新的进度条
                    } else {
                        mRootView.endLoadMore()//隐藏上拉加载更多的进度条
                    }
                }
                //使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .compose(RxLifecycleUtils.bindToLifecycle<BaseResponse<List<GankEntity>>>(mRootView))
                .subscribe(object : Observer<BaseResponse<List<GankEntity>>> {
                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseResponse<List<GankEntity>>) {
                        if (!t.isError) {
                            //如果是下拉刷新则清空列表
                            if (pullToRefresh) {
                                mList?.clear()
                            }
                            //更新之前列表总长度,用于确定加载更多的起始位置
                            preEndIndex = mList?.size ?: 0
                            mList?.addAll(t.results)
                            if (pullToRefresh) {
                                mAdapter?.notifyDataSetChanged()
                            } else {
                                if (t.results.isEmpty()) {
                                    mRootView.hasLoadedAll()
                                }
                                mAdapter?.notifyItemRangeInserted(preEndIndex, t.results.size)
                            }
                            mRootView.setEmpty(mList?.isEmpty() ?: false)
                        }

                    }
                })
    }

}
