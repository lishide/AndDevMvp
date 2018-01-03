package com.lishide.anddevmvp.di.component

import com.jess.arms.di.scope.ActivityScope

import dagger.Component

import com.jess.arms.di.component.AppComponent

import com.lishide.anddevmvp.di.module.WelfareModule

import com.lishide.anddevmvp.mvp.ui.fragment.WelfareFragment

@ActivityScope
@Component(modules = arrayOf(WelfareModule::class), dependencies = arrayOf(AppComponent::class))
interface WelfareComponent {
    fun inject(fragment: WelfareFragment)
}