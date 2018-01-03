package com.lishide.anddevmvp.di.component

import com.jess.arms.di.scope.ActivityScope

import dagger.Component

import com.jess.arms.di.component.AppComponent

import com.lishide.anddevmvp.di.module.MainModule

import com.lishide.anddevmvp.mvp.ui.activity.MainActivity

@ActivityScope
@Component(modules = arrayOf(MainModule::class), dependencies = arrayOf(AppComponent::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}