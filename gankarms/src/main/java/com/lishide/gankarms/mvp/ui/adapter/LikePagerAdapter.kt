package com.lishide.gankarms.mvp.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.lishide.gankarms.mvp.ui.fragment.ArticleFragment
import com.lishide.gankarms.mvp.ui.fragment.MeiziFragment

/**
 * 收藏 Fragment 适配器
 *
 * @author lishide
 * @date 2018/06/11
 */
class LikePagerAdapter(fm: FragmentManager, private val titles: List<String>) : FragmentStatePagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> MeiziFragment.newInstance()
            1 -> ArticleFragment.newInstance()
            else -> null
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

}
