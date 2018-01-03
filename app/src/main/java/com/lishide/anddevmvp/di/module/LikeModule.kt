package com.lishide.anddevmvp.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.lishide.anddevmvp.mvp.contract.LikeContract
import com.lishide.anddevmvp.mvp.model.LikeModel


@Module
class LikeModule
/**
 * 构建LikeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
 *
 * @param view
 */
(private val view: LikeContract.View) {

    @ActivityScope
    @Provides
    internal fun provideLikeView(): LikeContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideLikeModel(model: LikeModel): LikeContract.Model {
        return model
    }
}