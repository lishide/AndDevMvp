package com.lishide.gankarms.mvp.ui.adapter

import android.view.View
import com.jess.arms.base.BaseHolder
import com.jess.arms.base.DefaultAdapter
import com.lishide.gankarms.R
import com.lishide.gankarms.mvp.model.entity.GankEntity
import com.lishide.gankarms.mvp.ui.holder.GankItemHolder

/**
 * 干货列表适配器
 *
 * @author lishide
 * @date 2018/04/27
 */
class GankAdapter(infos: List<GankEntity.ResultsBean>) : DefaultAdapter<GankEntity.ResultsBean>(infos) {

    override fun getHolder(v: View, viewType: Int): BaseHolder<GankEntity.ResultsBean> = GankItemHolder(v)

    override fun getLayoutId(viewType: Int): Int = R.layout.recycle_item_gank
}
