package com.lishide.gankarms.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.di.module.MeiziModule
import com.lishide.gankarms.mvp.ui.fragment.MeiziFragment

import dagger.Component

@FragmentScope
@Component(modules = arrayOf(MeiziModule::class), dependencies = arrayOf(AppComponent::class))
interface MeiziComponent {
    fun inject(fragment: MeiziFragment)
}