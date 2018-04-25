package com.lishide.gankarms.di.module

import com.jess.arms.di.scope.ActivityScope
import com.lishide.gankarms.mvp.contract.WelfareContract
import com.lishide.gankarms.mvp.model.WelfareModel
import dagger.Module
import dagger.Provides

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