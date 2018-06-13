package com.lishide.gankarms.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.di.module.HomeChildSmartModule
import com.lishide.gankarms.mvp.ui.fragment.HomeChildSmartFragment

import dagger.Component

@FragmentScope
@Component(modules = arrayOf(HomeChildSmartModule::class), dependencies = arrayOf(AppComponent::class))
interface HomeChildSmartComponent {
    fun inject(fragment: HomeChildSmartFragment)
}