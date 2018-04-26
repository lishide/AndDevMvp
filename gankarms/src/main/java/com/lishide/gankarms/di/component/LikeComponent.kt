package com.lishide.gankarms.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.di.module.LikeModule
import com.lishide.gankarms.mvp.ui.fragment.LikeFragment
import dagger.Component

@FragmentScope
@Component(modules = arrayOf(LikeModule::class), dependencies = arrayOf(AppComponent::class))
interface LikeComponent {
    fun inject(fragment: LikeFragment)
}