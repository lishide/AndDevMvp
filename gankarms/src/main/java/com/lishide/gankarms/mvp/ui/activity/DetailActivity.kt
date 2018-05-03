package com.lishide.gankarms.mvp.ui.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import com.lishide.gankarms.R
import com.lishide.gankarms.di.component.DaggerDetailComponent
import com.lishide.gankarms.di.module.DetailModule
import com.lishide.gankarms.mvp.contract.DetailContract
import com.lishide.gankarms.mvp.model.entity.GankEntity
import com.lishide.gankarms.mvp.presenter.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlin.properties.Delegates

/**
 * 详情页面 DetailActivity
 *
 * @author lishide
 * @date 2018/04/27
 */
class DetailActivity : BaseActivity<DetailPresenter>(), DetailContract.View {

    private lateinit var entity: GankEntity
    private var isLike by Delegates.observable(false) { _, _, new ->
        fab.backgroundTintList = if (new) {
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent))
        } else {
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorD1))
        }
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .detailModule(DetailModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int = 0

    override fun initData(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_detail)

        val bundle = this.intent.extras
        entity = bundle.getSerializable(PARAM_GANK) as GankEntity

        this.title = entity.desc

        mPresenter?.getQuery(entity.id)

        fab.setOnClickListener {
            if (isLike) {
                mPresenter?.removeById(entity.id)
                showMsg("已从收藏夹移除")
            } else {
                mPresenter?.addToLike(entity)
                showMsg("已添加到收藏夹")
            }
        }
        initWebView()
    }

    private fun initWebView() {
        webView.apply {
            settings.let {
                it.useWideViewPort = true
                it.loadWithOverviewMode = true

                it.setSupportZoom(true)
                it.builtInZoomControls = true
                it.displayZoomControls = false
            }
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest?): Boolean = true
            }
            webChromeClient = object : WebChromeClient() {}
        }.loadUrl(entity.url)

    }

    override fun onLikeChange(isLike: Boolean) {
        this.isLike = isLike // 通过委托属性重置 FloatingActionButton 状态
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        checkNotNull(message)
        ArmsUtils.snackbarText(message)
    }

    /**
     * Show Snackbar message
     * 设置 View，避免 Snackbar 遮住 FAB。
     */
    private fun showMsg(msg: String) {
        checkNotNull(msg)
        Snackbar.make(mainContent, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun launchActivity(intent: Intent) {
        checkNotNull(intent)
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        onBackPressed()
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (webView != null) {
            (webView.parent as ViewGroup).removeView(webView)
            webView.destroy()
        }
        super.onDestroy()
    }

    companion object {
        const val PARAM_GANK = "gank"
    }
}
