package com.lishide.anddevmvp.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.jess.arms.base.BaseActivity
import com.jess.arms.base.DefaultAdapter
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.lishide.anddevmvp.R
import com.lishide.anddevmvp.di.component.DaggerUserComponent
import com.lishide.anddevmvp.di.module.UserModule
import com.lishide.anddevmvp.mvp.contract.UserContract
import com.lishide.anddevmvp.mvp.presenter.UserPresenter
import com.paginate.Paginate
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_user.*
import timber.log.Timber
import javax.inject.Inject

/**
 * 用户界面：展示 View 的用法
 *
 * @author lishide
 * @date 2017/11/09
 * @see [View wiki 官方文档](https://github.com/JessYanCoding/MVPArms/wiki.2.4.2)
 */
class UserActivity : BaseActivity<UserPresenter>(), UserContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var mRxPermissions: RxPermissions
    @Inject
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    @Inject
    lateinit var mAdapter: RecyclerView.Adapter<*>

    private var mPaginate: Paginate? = null
    private var isLoadingMore: Boolean = false

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerUserComponent
                .builder()
                .appComponent(appComponent)
                .userModule(UserModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int = R.layout.activity_user

    override fun initData(savedInstanceState: Bundle?) {
        initRecyclerView()
        recyclerView.adapter = mAdapter
        initPaginate()
    }

    /**
     * 初始化RecyclerView
     */
    private fun initRecyclerView() {
        swipeRefreshLayout.setOnRefreshListener(this)
        ArmsUtils.configRecyclerView(recyclerView, mLayoutManager)
    }

    override fun onRefresh() {
        mPresenter?.requestUsers(true)
    }

    override fun showLoading() {
        Timber.tag(TAG).w("showLoading")
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        Timber.tag(TAG).w("hideLoading")
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    /**
     * 开始加载更多
     */
    override fun startLoadMore() {
        isLoadingMore = true
    }

    /**
     * 结束加载更多
     */
    override fun endLoadMore() {
        isLoadingMore = false
    }

    override fun getActivity(): Activity = this

    override fun getRxPermissions(): RxPermissions = mRxPermissions

    /**
     * 初始化Paginate,用于加载更多
     */
    private fun initPaginate() {
        if (mPaginate == null) {
            val callbacks = object : Paginate.Callbacks {
                override fun onLoadMore() {
                    mPresenter?.requestUsers(false)
                }

                override fun isLoading(): Boolean = isLoadingMore

                override fun hasLoadedAllItems(): Boolean = false
            }

            mPaginate = Paginate.with(recyclerView, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build()
            mPaginate?.setHasMoreDataToLoad(false)
        }
    }

    override fun onDestroy() {
        //super.onDestroy()之后会unbind,所有view被置为null,所以必须在之前调用
        DefaultAdapter.releaseAllHolder(recyclerView)
        super.onDestroy()
        //this.mRxPermissions = null
        this.mPaginate = null
    }
}
