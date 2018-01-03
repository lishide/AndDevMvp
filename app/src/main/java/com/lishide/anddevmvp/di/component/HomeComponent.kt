package com.lishide.anddevmvp.di.component

import com.jess.arms.di.scope.ActivityScope

import dagger.Component

import com.jess.arms.di.component.AppComponent

import com.lishide.anddevmvp.di.module.HomeModule

import com.lishide.anddevmvp.mvp.ui.fragment.HomeFragment

@ActivityScope
@Component(modules = arrayOf(HomeModule::class), dependencies = arrayOf(AppComponent::class))
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}