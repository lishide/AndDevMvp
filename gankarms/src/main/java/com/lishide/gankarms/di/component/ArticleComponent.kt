package com.lishide.gankarms.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.di.module.ArticleModule
import com.lishide.gankarms.mvp.ui.fragment.ArticleFragment

import dagger.Component

@FragmentScope
@Component(modules = arrayOf(ArticleModule::class), dependencies = arrayOf(AppComponent::class))
interface ArticleComponent {
    fun inject(fragment: ArticleFragment)
}