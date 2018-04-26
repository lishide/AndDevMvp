package com.lishide.gankarms.di.module

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.jess.arms.di.scope.ActivityScope
import com.lishide.gankarms.mvp.contract.WelfareContract
import com.lishide.gankarms.mvp.model.WelfareModel
import com.lishide.gankarms.mvp.model.entity.GankEntity
import com.lishide.gankarms.mvp.ui.adapter.WelfareAdapter
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class WelfareModule
/**
 * 构建WelfareModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
 *
 * @param view
 */
(private val view: WelfareContract.View) {

    @ActivityScope
    @Provides
    internal fun provideWelfareView(): WelfareContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideWelfareModel(model: WelfareModel): WelfareContract.Model {
        return model
    }

    @ActivityScope
    @Provides
    internal fun provideLayoutManager(): RecyclerView.LayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    @ActivityScope
    @Provides
    internal fun provideStoreList(): ArrayList<GankEntity.ResultsBean> = ArrayList()

    @ActivityScope
    @Provides
    internal fun provideWelfareAdapter(list: ArrayList<GankEntity.ResultsBean>): RecyclerView.Adapter<*> =
            WelfareAdapter(list)
}