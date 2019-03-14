package com.lishide.gankarms.mvp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.Fragment
import android.view.inputmethod.InputMethodManager
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import com.lishide.gankarms.R
import com.lishide.gankarms.app.utils.EventBusTags
import com.lishide.gankarms.di.component.DaggerMainComponent
import com.lishide.gankarms.di.module.MainModule
import com.lishide.gankarms.mvp.contract.MainContract
import com.lishide.gankarms.mvp.presenter.MainPresenter
import com.lishide.gankarms.mvp.ui.fragment.HomeFragment
import com.lishide.gankarms.mvp.ui.fragment.LikeFragment
import com.lishide.gankarms.mvp.ui.fragment.WelfareFragment
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.OnTabSelectListener
import java.util.*

/**
 * 主页面 MainActivity
 *
 * @author lishide
 * @date 2018/01/03
 */
class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {

    private var mBottomBar: BottomBar? = null

    private var mNavIds: ArrayList<Int>? = null
    private var mReplace = 0
    private var homeFragment: HomeFragment? = null
    private var welfareFragment: WelfareFragment? = null
    private var likeFragment: LikeFragment? = null
    private var currentFragment: Fragment? = null
    private var mOnTabSelectListener = OnTabSelectListener { tabId ->
        when (tabId) {
            R.id.tab_home -> {
                switchFragment(currentFragment, homeFragment)
            }
            R.id.tab_welfare -> {
                if (welfareFragment == null) {
                    welfareFragment = WelfareFragment()
                }
                switchFragment(currentFragment, welfareFragment)
            }
            R.id.tab_like -> {
                if (likeFragment == null) {
                    likeFragment = LikeFragment()
                }
                switchFragment(currentFragment, likeFragment)
            }
        }
    }

    private fun switchFragment(from: Fragment?, to: Fragment?) {
        currentFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            if (currentFragment != to) {
                currentFragment = to
                if (to?.isAdded == false) {
                    transaction.hide(from!!).add(R.id.contentContainer, to)
                } else {
                    transaction.hide(from!!).show(to!!)
                }
            }
            transaction.commitAllowingStateLoss()
        }
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int = 0

    override fun initData(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        mBottomBar = findViewById(R.id.bottomBar)

        if (mNavIds == null) {
            mNavIds = ArrayList()
            mNavIds?.add(R.id.tab_home)
            mNavIds?.add(R.id.tab_welfare)
            mNavIds?.add(R.id.tab_like)
        }
        if (homeFragment == null) {
            homeFragment = HomeFragment()
        }
        supportFragmentManager.beginTransaction().add(R.id.contentContainer, homeFragment!!).commitAllowingStateLoss()
        currentFragment = homeFragment
        mBottomBar?.setOnTabSelectListener(mOnTabSelectListener)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

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
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        //保存当前Activity显示的Fragment索引
        outState?.putInt(EventBusTags.ACTIVITY_FRAGMENT_REPLACE, mReplace)
    }

    override fun onDestroy() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            InputMethodManager::class.java.getDeclaredMethod("windowDismissed", IBinder::class.java).invoke(imm,
                    window.decorView.windowToken)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        super.onDestroy()
        this.mNavIds = null
    }
}
