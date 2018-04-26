package com.lishide.gankarms.di.module

import com.jess.arms.di.scope.FragmentScope
import com.lishide.gankarms.mvp.contract.LikeContract
import com.lishide.gankarms.mvp.model.LikeModel
import dagger.Module
import dagger.Provides

@Module
class LikeModule
/**
 * 构建LikeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
 *
 * @param view
 */
(private val view: LikeContract.View) {

    @FragmentScope
    @Provides
    internal fun provideLikeView(): LikeContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    internal fun provideLikeModel(model: LikeModel): LikeContract.Model {
        return model
    }
}