package com.lishide.gankarms.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
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
import com.lishide.gankarms.mvp.presenter.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * 详情页面 DetailActivity
 *
 * @author lishide
 * @date 2018/04/27
 */
class DetailActivity : BaseActivity<DetailPresenter>(), DetailContract.View {

    private var titleStr = ""
    private var url = ""

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
        titleStr = bundle.getString("title")
        url = bundle.getString("url")

        this.title = titleStr

        fab.setOnClickListener {
            showMsg("fab click")
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
        }.loadUrl(url)

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
}
