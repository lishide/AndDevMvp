package com.lishide.gankarms.di.module

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.mvp.contract.HomeChildContract
import com.lishide.gankarms.mvp.model.HomeChildModel
import com.lishide.gankarms.mvp.model.entity.GankEntity
import com.lishide.gankarms.mvp.ui.adapter.GankAdapter
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class HomeChildModule
/**
 * 构建HomeChildModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
 *
 * @param view
 */
(private val view: HomeChildContract.View) {

    @FragmentScope
    @Provides
    internal fun provideHomeChildView(): HomeChildContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    internal fun provideHomeChildModel(model: HomeChildModel): HomeChildContract.Model {
        return model
    }

    @FragmentScope
    @Provides
    internal fun provideLayoutManager(): RecyclerView.LayoutManager =
            LinearLayoutManager(view.getContext())

    @FragmentScope
    @Provides
    internal fun provideList(): ArrayList<GankEntity.ResultsBean> = ArrayList()

    @FragmentScope
    @Provides
    internal fun provideAdapter(list: ArrayList<GankEntity.ResultsBean>): RecyclerView.Adapter<*> =
            GankAdapter(list)
}