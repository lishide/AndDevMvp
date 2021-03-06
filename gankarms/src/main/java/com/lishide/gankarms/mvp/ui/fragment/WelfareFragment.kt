package com.lishide.gankarms.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jess.arms.base.BaseFragment
import com.jess.arms.base.DefaultAdapter
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import com.lishide.gankarms.R
import com.lishide.gankarms.di.component.DaggerWelfareComponent
import com.lishide.gankarms.di.module.WelfareModule
import com.lishide.gankarms.mvp.contract.WelfareContract
import com.lishide.gankarms.mvp.presenter.WelfarePresenter
import com.paginate.Paginate
import kotlinx.android.synthetic.main.layout_empty_list.*
import kotlinx.android.synthetic.main.layout_refresh_list.*
import javax.inject.Inject

/**
 * 福利 Fragment
 *
 * @author lishide
 * @date 2018/01/03
 */
class WelfareFragment : BaseFragment<WelfarePresenter>(), WelfareContract.View,
        SwipeRefreshLayout.OnRefreshListener {
    @Inject
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    @Inject
    lateinit var mAdapter: RecyclerView.Adapter<*>

    private var mPaginate: Paginate? = null
    private var isLoadingMore: Boolean = false
    private var isLoadedAll: Boolean = false

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerWelfareComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .welfareModule(WelfareModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_welfare, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        swipeRefreshLayout.setOnRefreshListener(this)
        ArmsUtils.configRecyclerView(recyclerView, mLayoutManager)

        recyclerView.adapter = mAdapter
        initPaginate()
        mPresenter?.requestData(true)
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     *
     *
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */
    override fun setData(data: Any?) {

    }

    override fun onRefresh() {
        mPresenter?.requestData(true)
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showMessage(message: String) {
        checkNotNull(message)
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        checkNotNull(intent)
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {

    }

    override fun getContext(): FragmentActivity? = activity

    override fun startLoadMore() {
        isLoadingMore = true
    }

    override fun endLoadMore() {
        isLoadingMore = false
    }

    override fun hasLoadedAll() {
        isLoadedAll = true
    }

    override fun setEmpty(isEmpty: Boolean) {
        recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
        ll_empty.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private fun initPaginate() {
        if (mPaginate == null) {
            val callbacks = object : Paginate.Callbacks {
                override fun onLoadMore() {
                    mPresenter?.requestData(false)
                }

                override fun isLoading(): Boolean = isLoadingMore

                override fun hasLoadedAllItems(): Boolean = isLoadedAll
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
        this.mPaginate = null
    }

    companion object {


        fun newInstance(): WelfareFragment {
            return WelfareFragment()
        }
    }

}
