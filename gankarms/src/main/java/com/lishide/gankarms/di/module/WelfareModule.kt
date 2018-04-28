package com.lishide.gankarms.di.module

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.jess.arms.di.scope.FragmentScope
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

    @FragmentScope
    @Provides
    internal fun provideWelfareView(): WelfareContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    internal fun provideWelfareModel(model: WelfareModel): WelfareContract.Model {
        return model
    }

    @FragmentScope
    @Provides
    internal fun provideLayoutManager(): RecyclerView.LayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    @FragmentScope
    @Provides
    internal fun provideStoreList(): ArrayList<GankEntity> = ArrayList()

    @FragmentScope
    @Provides
    internal fun provideWelfareAdapter(list: ArrayList<GankEntity>): RecyclerView.Adapter<*> =
            WelfareAdapter(list)
}