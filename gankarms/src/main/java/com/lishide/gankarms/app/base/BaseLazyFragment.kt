package com.lishide.gankarms.app.base

import android.os.Bundle
import android.view.View
import com.jess.arms.base.BaseFragment
import com.jess.arms.mvp.IPresenter

/**
 * 懒加载 Fragment 基础类
 *
 * @author lishide
 * @date 2018/06/12
 */
abstract class BaseLazyFragment<P : IPresenter> : BaseFragment<P>() {
    /**
     * 控件是否已经初始化
     */
    private var isCreateView = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCreateView = true
        if (userVisibleHint) {
            initPrepare()
        }
    }

    //此方法在控件初始化前调用，所以不能在此方法中直接操作控件会出现空指针
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            initPrepare()
        }
    }

    @Synchronized
    private fun initPrepare() {
        if (!isCreateView) {
            return
        }
        onUserVisible()
    }

    /**
     * 当视图初始化，并且对用户可见的时候去真正的加载数据
     */
    protected abstract fun onUserVisible()
}
