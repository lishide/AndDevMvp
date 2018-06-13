package com.lishide.gankarms.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lishide.gankarms.R
import com.lishide.gankarms.app.constant.CategoryConstant
import com.lishide.gankarms.mvp.model.entity.GankEntity
import com.lishide.gankarms.mvp.ui.activity.DetailActivity
import org.jetbrains.anko.startActivity

/**
 * 干货列表适配器
 *
 * @author lishide
 * @date 2018/06/11
 */
class ArticleAdapter(data: List<GankEntity>?) : BaseQuickAdapter<GankEntity, BaseViewHolder>(R.layout.recycle_item_gank, data) {

    override fun convert(helper: BaseViewHolder?, item: GankEntity?) {
        helper?.setText(R.id.tvDesc, item?.desc)
        helper?.setText(R.id.tvAuthor, item?.who)
        helper?.setText(R.id.tvDate, item?.publishedAt)

        val ivImage = helper?.getView<ImageView>(R.id.ivImage)
        val imgResId: Int =
                when (item?.type) {
                    CategoryConstant.ANDROID_STR -> R.drawable.ic_android
                    CategoryConstant.IOS_STR -> R.drawable.ic_apple
                    CategoryConstant.QIAN_STR -> R.drawable.ic_html
                    else -> 0
                }
        ivImage?.setImageResource(imgResId)

        helper?.itemView?.setOnClickListener {
            it.context.startActivity<DetailActivity>(DetailActivity.PARAM_GANK to item)
        }
    }
}
