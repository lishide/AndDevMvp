package com.lishide.gankarms.di.module

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.mvp.contract.HomeChildSmartContract
import com.lishide.gankarms.mvp.model.HomeChildSmartModel
import com.lishide.gankarms.mvp.model.entity.GankEntity
import com.lishide.gankarms.mvp.ui.adapter.ArticleAdapter
import dagger.Module
import dagger.Provides
import java.util.*


@Module
class HomeChildSmartModule
/**
 * 构建HomeChildSmartModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
 *
 * @param view
 */
(private val view: HomeChildSmartContract.View) {

    @FragmentScope
    @Provides
    internal fun provideHomeChildSmartView(): HomeChildSmartContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    internal fun provideHomeChildSmartModel(model: HomeChildSmartModel): HomeChildSmartContract.Model {
        return model
    }

    @FragmentScope
    @Provides
    internal fun provideLayoutManager(): RecyclerView.LayoutManager =
            LinearLayoutManager(view.getContext())

    @FragmentScope
    @Provides
    internal fun provideList(): ArrayList<GankEntity> = ArrayList()

    @FragmentScope
    @Provides
    internal fun provideAdapter(list: ArrayList<GankEntity>): RecyclerView.Adapter<*> =
            ArticleAdapter(list)
}