package com.lishide.anddevmvp.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.lishide.anddevmvp.di.module.UserModule
import com.lishide.anddevmvp.mvp.ui.activity.UserActivity
import dagger.Component

/**
 * 展示 Component 的用法
 *
 * @author lishide
 * @date 2017/11/09
 */
@ActivityScope
@Component(modules = arrayOf(UserModule::class), dependencies = arrayOf(AppComponent::class))
interface UserComponent {
    fun inject(activity: UserActivity)
}