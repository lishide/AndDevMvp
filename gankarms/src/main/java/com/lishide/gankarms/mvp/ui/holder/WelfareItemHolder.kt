package com.lishide.gankarms.mvp.ui.holder

import android.view.View
import android.widget.ImageView
import com.jess.arms.base.BaseHolder
import com.jess.arms.di.component.AppComponent
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.lishide.gankarms.R
import com.lishide.gankarms.mvp.model.entity.GankEntity

/**
 * 福利 Item Holder
 *
 * @author lishide
 * @date 2017/11/09
 */
class WelfareItemHolder(itemView: View) : BaseHolder<GankEntity.ResultsBean>(itemView) {

    private var mAvatar: ImageView? = null
    private val mAppComponent: AppComponent
    /**
     * 用于加载图片的管理类,默认使用 Glide,使用策略模式,可替换框架
     */
    private val mImageLoader: ImageLoader

    init {
        with(itemView) {
            mAvatar = this.findViewById(R.id.iv_avatar)
        }
        //可以在任何可以拿到 Context 的地方,拿到 AppComponent,从而得到用 Dagger 管理的单例对象
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.context)
        mImageLoader = mAppComponent.imageLoader()
    }

    override fun setData(data: GankEntity.ResultsBean, position: Int) {
        //itemView 的 Context 就是 Activity, Glide 会自动处理并和该 Activity 的生命周期绑定
        mImageLoader.loadImage(itemView.context,
                ImageConfigImpl
                        .builder()
                        .url(data.url)
                        .imageView(mAvatar)
                        .build())
    }

    override fun onRelease() {
        mImageLoader.clear(mAppComponent.application(), ImageConfigImpl.builder()
                .imageViews(mAvatar)
                .build())
    }
}
