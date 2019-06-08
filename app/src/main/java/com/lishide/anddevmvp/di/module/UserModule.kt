package com.lishide.anddevmvp.di.module

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jess.arms.di.scope.ActivityScope
import com.lishide.anddevmvp.mvp.contract.UserContract
import com.lishide.anddevmvp.mvp.model.UserModel
import com.lishide.anddevmvp.mvp.model.entity.User
import com.lishide.anddevmvp.mvp.ui.adapter.UserAdapter
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import java.util.*

/**
 * 展示 Module 的用法
 *
 * @author lishide
 * @date 2017/11/09
 * @see [Module wiki 官方文档](https://github.com/JessYanCoding/MVPArms/wiki.2.4.5)
 */
@Module
class UserModule
/**
 * 构建 UserModule 时,将 View 的实现类传进来,这样就可以提供 View 的实现类给 Presenter
 *
 * @param view
 */
(private val view: UserContract.View) {

    @ActivityScope
    @Provides
    internal fun provideUserView(): UserContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideUserModel(model: UserModel): UserContract.Model {
        return model
    }

    @ActivityScope
    @Provides
    internal fun provideRxPermissions(): RxPermissions {
        return RxPermissions(view.getActivity() as androidx.fragment.app.FragmentActivity)
    }

    @ActivityScope
    @Provides
    internal fun provideLayoutManager(): androidx.recyclerview.widget.RecyclerView.LayoutManager {
        return GridLayoutManager(view.getActivity(), 2)
    }

    @ActivityScope
    @Provides
    internal fun provideUserList(): ArrayList<User> {
        return ArrayList()
    }

    @ActivityScope
    @Provides
    internal fun provideUserAdapter(list: ArrayList<User>): androidx.recyclerview.widget.RecyclerView.Adapter<*> {
        return UserAdapter(list)
    }
}
