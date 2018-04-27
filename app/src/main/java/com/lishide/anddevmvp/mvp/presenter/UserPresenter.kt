package com.lishide.anddevmvp.mvp.presenter

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.Fragment
import android.support.v4.app.SupportActivity
import android.support.v7.widget.RecyclerView
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.PermissionUtil
import com.jess.arms.utils.RxLifecycleUtils
import com.lishide.anddevmvp.mvp.contract.UserContract
import com.lishide.anddevmvp.mvp.model.entity.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import javax.inject.Inject

/**
 * 展示 Presenter 的用法
 *
 * @author lishide
 * @date 2017/11/09
 * @see [Presenter wiki 官方文档](https://github.com/JessYanCoding/MVPArms/wiki.2.4.4)
 */
@ActivityScope
class UserPresenter @Inject
constructor(
        model: UserContract.Model,
        rootView: UserContract.View,
        private var mErrorHandler: RxErrorHandler?,
        private var mAppManager: AppManager?,
        private var mApplication: Application?,
        private var mUsers: ArrayList<User>?,
        private var mAdapter: RecyclerView.Adapter<*>?
) : BasePresenter<UserContract.Model, UserContract.View>(model, rootView) {
    private var lastUserId = 1
    private var isFirst = true
    private var preEndIndex: Int = 0

    /**
     * 使用 2017 Google IO 发布的 Architecture Components 中的 Lifecycles 的新特性 (此特性已被加入 Support library)
     * 使 `Presenter` 可以与 [SupportActivity] 和 [Fragment] 的部分生命周期绑定
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    internal fun onCreate() {
        //打开 App 时自动加载列表
        requestUsers(true)
    }

    fun requestUsers(pullToRefresh: Boolean) {
        //请求外部存储权限用于适配android6.0的权限管理机制
        PermissionUtil.externalStorage(object : PermissionUtil.RequestPermission {
            override fun onRequestPermissionSuccess() {
                //request permission success, do something.
            }

            override fun onRequestPermissionFailure(permissions: List<String>) {
                mRootView.showMessage("Request permissions failure")
            }

            override fun onRequestPermissionFailureWithAskNeverAgain(permissions: List<String>) {
                mRootView.showMessage("Need to go to the settings")
            }
        }, mRootView.getRxPermissions(), mErrorHandler)

        //下拉刷新默认只请求第一页
        if (pullToRefresh) {
            lastUserId = 1
        }

        //关于RxCache缓存库的使用请参考 http://www.jianshu.com/p/b58ef6b0624b
        //是否驱逐缓存,为ture即不使用缓存,每次下拉刷新即需要最新数据,则不使用缓存
        var isEvictCache = pullToRefresh

        if (pullToRefresh && isFirst) {
            //默认在第一次下拉刷新时使用缓存
            isFirst = false
            isEvictCache = false
        }

        mModel.getUsers(lastUserId, isEvictCache)
                .subscribeOn(Schedulers.io())
                //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .retryWhen(RetryWithDelay(3, 2))
                .doOnSubscribe { _ ->
                    if (pullToRefresh) {
                        mRootView.showLoading()//显示下拉刷新的进度条
                    } else {
                        mRootView.startLoadMore()//显示上拉加载更多的进度条
                    }
                }.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    if (pullToRefresh) {
                        mRootView.hideLoading()//隐藏下拉刷新的进度条
                    } else {
                        mRootView.endLoadMore()//隐藏上拉加载更多的进度条
                    }
                }
                //使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(object : ErrorHandleSubscriber<List<User>>(mErrorHandler) {
                    override fun onNext(users: List<User>) {
                        //记录最后一个id,用于下一次请求
                        lastUserId = users[users.size - 1].id
                        //如果是下拉刷新则清空列表
                        if (pullToRefresh) {
                            mUsers?.clear()
                        }
                        //更新之前列表总长度,用于确定加载更多的起始位置
                        preEndIndex = mUsers?.size ?: 0
                        mUsers?.addAll(users)
                        if (pullToRefresh) {
                            mAdapter?.notifyDataSetChanged()
                        } else {
                            mAdapter?.notifyItemRangeInserted(preEndIndex, users.size)
                        }
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.mAdapter = null
        this.mUsers = null
        this.mErrorHandler = null
        this.mAppManager = null
        this.mApplication = null
    }
}
