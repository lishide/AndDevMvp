package com.lishide.anddevmvp.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.lishide.anddevmvp.mvp.contract.WelfareContract
import com.lishide.anddevmvp.mvp.model.WelfareModel


@Module
class WelfareModule
/**
 * 构建WelfareModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
 *
 * @param view
 */
(private val view: WelfareContract.View) {

    @ActivityScope
    @Provides
    internal fun provideWelfareView(): WelfareContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideWelfareModel(model: WelfareModel): WelfareContract.Model {
        return model
    }
}