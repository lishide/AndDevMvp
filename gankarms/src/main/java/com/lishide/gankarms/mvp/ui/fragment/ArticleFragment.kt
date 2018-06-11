package com.lishide.gankarms.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import com.lishide.gankarms.R
import com.lishide.gankarms.di.component.DaggerArticleComponent
import com.lishide.gankarms.di.module.ArticleModule
import com.lishide.gankarms.mvp.contract.ArticleContract
import com.lishide.gankarms.mvp.presenter.ArticlePresenter
import kotlinx.android.synthetic.main.layout_empty_list.*
import kotlinx.android.synthetic.main.layout_refresh_list.*
import javax.inject.Inject

/**
 * 文章 Fragment
 *
 * @author lishide
 * @date 2018/06/11
 */
class ArticleFragment : BaseFragment<ArticlePresenter>(), ArticleContract.View,
        SwipeRefreshLayout.OnRefreshListener {
    //控件是否已经初始化
    private var isCreateView: Boolean = false
    //是否已经加载过数据
    private var isLoadData = false

    @Inject
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    @Inject
    lateinit var mAdapter: RecyclerView.Adapter<*>

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerArticleComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .articleModule(ArticleModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        swipeRefreshLayout.setOnRefreshListener(this)
        ArmsUtils.configRecyclerView(recyclerView, mLayoutManager)

        recyclerView.adapter = mAdapter

        if (userVisibleHint) {
            lazyLoad()
        }
        isCreateView = true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isCreateView) {
            lazyLoad()
        }
    }

    private fun lazyLoad() {
        //如果没有加载过就加载，否则就不再加载了
        if (!isLoadData) {
            //加载数据操作
            mPresenter?.requestData(true)
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

    override fun setEmpty(isEmpty: Boolean) {
        recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
        ll_empty.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    companion object {

        fun newInstance(): ArticleFragment {
            return ArticleFragment()
        }
    }
}
