package com.lishide.gankarms.mvp.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.lishide.gankarms.mvp.ui.fragment.HomeChildFragment
import com.lishide.gankarms.mvp.ui.fragment.HomeChildSmartFragment

/**
 * 主页 Fragment 适配器
 *
 * @author lishide
 * @date 2018/04/26
 */
class HomePagerAdapter(fm: FragmentManager, private val titles: List<String>) : FragmentStatePagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getItem(position: Int): Fragment {
        // 使用 SmartRefreshLayout 做刷新的 Fragment，以后可常用
        if (position == 0) {
            return HomeChildSmartFragment.newInstance(titles[position])
        }
        return HomeChildFragment.newInstance(titles[position])
    }

    override fun getCount(): Int {
        return titles.size
    }

}
