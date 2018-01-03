package com.lishide.anddevmvp.mvp.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.lishide.anddevmvp.R;
import com.lishide.anddevmvp.mvp.model.entity.User;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * 用户Item Holder：展示 {@link BaseHolder} 的用法
 *
 * @author lishide
 * @date 2017/11/09
 */
public class UserItemHolder extends BaseHolder<User> {

    @BindView(R.id.iv_avatar)
    ImageView mAvatar;
    @BindView(R.id.tv_name)
    TextView mName;
    private AppComponent mAppComponent;
    /**
     * 用于加载图片的管理类,默认使用 Glide,使用策略模式,可替换框架
     */
    private ImageLoader mImageLoader;

    public UserItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到 Context 的地方,拿到 AppComponent,从而得到用 Dagger 管理的单例对象
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(User data, int position) {
        Observable.just(data.getLogin())
                .subscribe(s -> mName.setText(s));

        //itemView 的 Context 就是 Activity, Glide 会自动处理并和该 Activity 的生命周期绑定
        mImageLoader.loadImage(itemView.getContext(),
                ImageConfigImpl
                        .builder()
                        .url(data.getAvatarUrl())
                        .imageView(mAvatar)
                        .build());
    }

    @Override
    protected void onRelease() {
        mImageLoader.clear(mAppComponent.application(), ImageConfigImpl.builder()
                .imageViews(mAvatar)
                .build());
    }
}
