package com.lishide.gankarms.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.lishide.gankarms.di.module.DetailModule
import com.lishide.gankarms.mvp.ui.activity.DetailActivity
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(DetailModule::class), dependencies = arrayOf(AppComponent::class))
interface DetailComponent {
    fun inject(activity: DetailActivity)
}