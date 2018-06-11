package com.lishide.gankarms.di.module

import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.mvp.contract.ArticleContract
import com.lishide.gankarms.mvp.model.ArticleModel

import dagger.Module
import dagger.Provides

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
}