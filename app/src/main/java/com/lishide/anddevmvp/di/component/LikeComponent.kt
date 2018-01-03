package com.lishide.anddevmvp.di.component

import com.jess.arms.di.scope.ActivityScope

import dagger.Component

import com.jess.arms.di.component.AppComponent

import com.lishide.anddevmvp.di.module.LikeModule

import com.lishide.anddevmvp.mvp.ui.fragment.LikeFragment

@ActivityScope
@Component(modules = arrayOf(LikeModule::class), dependencies = arrayOf(AppComponent::class))
interface LikeComponent {
    fun inject(fragment: LikeFragment)
}