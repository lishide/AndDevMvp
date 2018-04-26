package com.lishide.gankarms.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.di.module.HomeChildModule
import com.lishide.gankarms.mvp.ui.fragment.HomeChildFragment

import dagger.Component

@FragmentScope
@Component(modules = arrayOf(HomeChildModule::class), dependencies = arrayOf(AppComponent::class))
interface HomeChildComponent {
    fun inject(fragment: HomeChildFragment)
}