package com.lishide.gankarms.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.lishide.gankarms.di.module.WelfareModule
import com.lishide.gankarms.mvp.ui.fragment.WelfareFragment
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(WelfareModule::class), dependencies = arrayOf(AppComponent::class))
interface WelfareComponent {
    fun inject(fragment: WelfareFragment)
}