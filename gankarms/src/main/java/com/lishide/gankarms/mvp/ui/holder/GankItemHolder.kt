package com.lishide.gankarms.mvp.ui.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.jess.arms.base.BaseHolder
import com.jess.arms.di.component.AppComponent
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.utils.ArmsUtils
import com.lishide.gankarms.R
import com.lishide.gankarms.app.constant.CategoryConstant
import com.lishide.gankarms.mvp.model.entity.GankEntity
import com.lishide.gankarms.mvp.ui.activity.DetailActivity
import org.jetbrains.anko.startActivity

/**
 * 干货 Item Holder
 *
 * @author lishide
 * @date 2018/04/27
 */
class GankItemHolder(itemView: View) : BaseHolder<GankEntity.ResultsBean>(itemView) {

    private var ivImage: ImageView? = null
    private var tvDesc: TextView? = null
    private var tvAuthor: TextView? = null
    private var tvDate: TextView? = null
    private val mAppComponent: AppComponent
    /**
     * 用于加载图片的管理类,默认使用 Glide,使用策略模式,可替换框架
     */
    private val mImageLoader: ImageLoader

    init {
        with(itemView) {
            ivImage = this.findViewById(R.id.ivImage)
            tvDesc = this.findViewById(R.id.tvDesc)
            tvAuthor = this.findViewById(R.id.tvAuthor)
            tvDate = this.findViewById(R.id.tvDate)
        }
        //可以在任何可以拿到 Context 的地方,拿到 AppComponent,从而得到用 Dagger 管理的单例对象
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.context)
        mImageLoader = mAppComponent.imageLoader()
    }

    override fun setData(data: GankEntity.ResultsBean, position: Int) {
        tvAuthor?.text = data.who
        tvDesc?.text = data.desc
        tvDate?.text = data.publishedAt

        val imgResId: Int =
                when (data.type) {
                    CategoryConstant.ANDROID_STR -> R.drawable.ic_android
                    CategoryConstant.IOS_STR -> R.drawable.ic_apple
                    CategoryConstant.QIAN_STR -> R.drawable.ic_html
                    else -> 0
                }
        ivImage?.setImageResource(imgResId)

        itemView.setOnClickListener {
            it.context.startActivity<DetailActivity>("title" to data.desc, "url" to data.url)
        }
    }

    override fun onRelease() {

    }
}
