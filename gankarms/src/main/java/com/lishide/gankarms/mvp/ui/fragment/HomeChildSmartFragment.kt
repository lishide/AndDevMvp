package com.lishide.gankarms.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import com.lishide.gankarms.R
import com.lishide.gankarms.app.base.BaseLazyFragment
import com.lishide.gankarms.di.component.DaggerHomeChildSmartComponent
import com.lishide.gankarms.di.module.HomeChildSmartModule
import com.lishide.gankarms.mvp.contract.HomeChildSmartContract
import com.lishide.gankarms.mvp.presenter.HomeChildSmartPresenter
import kotlinx.android.synthetic.main.layout_smart_refresh_list.*
import javax.inject.Inject

/**
 * 首页子 Fragment（使用 SmartRefreshLayout 做刷新）
 *
 * @author lishide
 * @date 2018/06/12
 */
class HomeChildSmartFragment : BaseLazyFragment<HomeChildSmartPresenter>(), HomeChildSmartContract.View {
    //是否已经加载过数据
    private var isLoadData = false

    private var type: String? = null

    @Inject
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    @Inject
    lateinit var mAdapter: RecyclerView.Adapter<*>

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeChildSmartComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeChildSmartModule(HomeChildSmartModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.layout_smart_refresh_list, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        ArmsUtils.configRecyclerView(recyclerView, mLayoutManager)
        recyclerView.adapter = mAdapter

        refreshLayout.setOnLoadMoreListener {
            mPresenter?.requestData(type, false)
        }
        refreshLayout.setOnRefreshListener {
            mPresenter?.requestData(type, true)
        }
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 [Message], 通过 what 字段来区分不同的方法, 在 [.setData]
     * 方法中就可以 `switch` 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     *
     *
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 [.setData] 方法时 [Fragment.onCreate] 还没执行
     * 但在 [.setData] 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 [Fragment.onCreate] 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 [.setData], 在 [.initData] 中初始化就可以了
     *
     *
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     * if (data != null && data instanceof Message) {
     * switch (((Message) data).what) {
     * case 0:
     * loadData(((Message) data).arg1);
     * break;
     * case 1:
     * refreshUI();
     * break;
     * default:
     * //do something
     * break;
     * }
     * }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
    </pre> *
     *
     * @param data 当不需要参数时 `data` 可以为 `null`
     */
    override fun setData(data: Any?) {

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
        refreshLayout.finishRefresh()
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

    override fun onUserVisible() {
        type = arguments?.getString(ARG_PARAM)
        //如果没有加载过就加载，否则就不再加载了
        if (!isLoadData) {
            //加载数据操作
            refreshLayout.autoRefresh()
        }
    }

    override fun getContext(): FragmentActivity? = activity

    override fun startLoadMore() {
    }

    override fun endLoadMore() {
        refreshLayout.finishLoadMore()
    }

    override fun hasLoadedAll() {
        //将不会再次触发加载更多事件
        refreshLayout.finishLoadMoreWithNoMoreData()
    }

    override fun setEmpty(isEmpty: Boolean) {
        isLoadData = true
    }

    companion object {
        private const val ARG_PARAM = "cateName"

        fun newInstance(cateName: String): HomeChildSmartFragment {
            val fragment = HomeChildSmartFragment()
            val bundle = Bundle()
            bundle.putString(ARG_PARAM, cateName)
            fragment.arguments = bundle
            return fragment
        }
    }
}
