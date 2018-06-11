package com.lishide.gankarms.di.module

import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.mvp.contract.MeiziContract
import com.lishide.gankarms.mvp.model.MeiziModel

import dagger.Module
import dagger.Provides

@Module
class MeiziModule
/**
 * 构建MeiziModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
 *
 * @param view
 */
(private val view: MeiziContract.View) {

    @FragmentScope
    @Provides
    internal fun provideMeiziView(): MeiziContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    internal fun provideMeiziModel(model: MeiziModel): MeiziContract.Model {
        return model
    }
}