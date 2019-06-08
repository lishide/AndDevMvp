package com.lishide.anddevmvp.mvp.ui.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.jess.arms.base.BaseHolder
import com.jess.arms.di.component.AppComponent
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.lishide.anddevmvp.R
import com.lishide.anddevmvp.mvp.model.entity.User
import io.reactivex.Observable
import me.jessyan.armscomponent.commonsdk.imgaEngine.config.CommonImageConfigImpl

/**
 * 用户Item Holder：展示 [BaseHolder] 的用法
 *
 * @author lishide
 * @date 2017/11/09
 */
class UserItemHolder(itemView: View) : BaseHolder<User>(itemView) {

    private var mAvatar: ImageView? = null
    private var mTvName: TextView? = null
    private val mAppComponent: AppComponent
    /**
     * 用于加载图片的管理类,默认使用 Glide,使用策略模式,可替换框架
     */
    private val mImageLoader: ImageLoader

    init {
        with(itemView) {
            mAvatar = itemView.findViewById(R.id.iv_avatar)
            mTvName = itemView.findViewById(R.id.tv_name)
        }
        //可以在任何可以拿到 Context 的地方,拿到 AppComponent,从而得到用 Dagger 管理的单例对象
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.context)
        mImageLoader = mAppComponent.imageLoader()
    }

    override fun setData(data: User, position: Int) {
        Observable.just(data.login)
                .subscribe { s -> mTvName?.text = s }

        //itemView 的 Context 就是 Activity, Glide 会自动处理并和该 Activity 的生命周期绑定
        mImageLoader.loadImage(itemView.context,
                CommonImageConfigImpl
                        .builder()
                        .url(data.avatarUrl)
                        .imageView(mAvatar)
                        .build())
    }

    /**
     * 在 Activity 的 onDestroy 中使用 {@link DefaultAdapter#releaseAllHolder(RecyclerView)} 方法 (super.onDestroy() 之前)
     * {@link BaseHolder#onRelease()} 才会被调用, 可以在此方法中释放一些资源
     */
    override fun onRelease() {
        //只要传入的 Context 为 Activity, Glide 就会自己做好生命周期的管理, 其实在上面的代码中传入的 Context 就是 Activity
        //所以在 onRelease 方法中不做 clear 也是可以的, 但是在这里想展示一下 clear 的用法
        mImageLoader.clear(mAppComponent.application(), ImageConfigImpl.builder()
                .imageViews(mAvatar)
                .build())
        this.mAvatar = null
        this.mTvName = null
    }
}
