package com.lishide.gankarms.mvp.ui.adapter

import android.view.View

import com.jess.arms.base.BaseHolder
import com.jess.arms.base.DefaultAdapter
import com.lishide.gankarms.R
import com.lishide.gankarms.mvp.model.entity.GankEntity
import com.lishide.gankarms.mvp.ui.holder.WelfareItemHolder

/**
 * 福利列表适配器
 *
 * @author lishide
 * @date 2018/04/25
 */
class WelfareAdapter(infos: List<GankEntity.ResultsBean>) : DefaultAdapter<GankEntity.ResultsBean>(infos) {

    override fun getHolder(v: View, viewType: Int): BaseHolder<GankEntity.ResultsBean> = WelfareItemHolder(v)

    override fun getLayoutId(viewType: Int): Int = R.layout.recycle_item_gril
}
