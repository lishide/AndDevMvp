package com.lishide.gankarms.di.module

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.mvp.contract.ArticleContract
import com.lishide.gankarms.mvp.model.ArticleModel
import com.lishide.gankarms.mvp.model.entity.GankEntity
import com.lishide.gankarms.mvp.ui.adapter.ArticleAdapter
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class ArticleModule
/**
 * 构建ArticleModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
 *
 * @param view
 */
(private val view: ArticleContract.View) {

    @FragmentScope
    @Provides
    internal fun provideArticleView(): ArticleContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    internal fun provideArticleModel(model: ArticleModel): ArticleContract.Model {
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