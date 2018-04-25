package com.lishide.gankarms.mvp.ui.adapter

import android.view.View

import com.jess.arms.base.BaseHolder
import com.jess.arms.base.DefaultAdapter
import com.lishide.gankarms.R
import com.lishide.gankarms.mvp.model.entity.GankEntity
import com.lishide.gankarms.mvp.ui.holder.WelfareItemHolder

/**
 * 用户列表适配器：展示 [DefaultAdapter] 的用法
 *
 * @author lishide
 * @date 2017/11/09
 */
class WelfareAdapter(infos: List<GankEntity.ResultsBean>) : DefaultAdapter<GankEntity.ResultsBean>(infos) {

    override fun getHolder(v: View, viewType: Int): BaseHolder<GankEntity.ResultsBean> = WelfareItemHolder(v)

    override fun getLayoutId(viewType: Int): Int = R.layout.recycle_list
}
