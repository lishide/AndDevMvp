package com.lishide.gankarms.di.module

import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.mvp.contract.HomeContract
import com.lishide.gankarms.mvp.model.HomeModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule
/**
 * 构建HomeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
 *
 * @param view
 */
(private val view: HomeContract.View) {

    @FragmentScope
    @Provides
    internal fun provideHomeView(): HomeContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    internal fun provideHomeModel(model: HomeModel): HomeContract.Model {
        return model
    }
}