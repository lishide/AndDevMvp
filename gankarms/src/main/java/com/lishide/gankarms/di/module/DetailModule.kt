package com.lishide.gankarms.di.module

import com.jess.arms.di.scope.ActivityScope
import com.lishide.gankarms.mvp.contract.DetailContract
import com.lishide.gankarms.mvp.model.DetailModel
import dagger.Module
import dagger.Provides

@Module
class DetailModule
/**
 * 构建DetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
 *
 * @param view
 */
(private val view: DetailContract.View) {

    @ActivityScope
    @Provides
    internal fun provideDetailView(): DetailContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideDetailModel(model: DetailModel): DetailContract.Model {
        return model
    }
}