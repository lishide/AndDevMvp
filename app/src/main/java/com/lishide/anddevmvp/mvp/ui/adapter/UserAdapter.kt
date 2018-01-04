package com.lishide.anddevmvp.mvp.ui.adapter

import android.view.View

import com.jess.arms.base.BaseHolder
import com.jess.arms.base.DefaultAdapter
import com.lishide.anddevmvp.R
import com.lishide.anddevmvp.mvp.model.entity.User
import com.lishide.anddevmvp.mvp.ui.holder.UserItemHolder

/**
 * 用户列表适配器：展示 [DefaultAdapter] 的用法
 *
 * @author lishide
 * @date 2017/11/09
 */
class UserAdapter(infos: List<User>) : DefaultAdapter<User>(infos) {

    override fun getHolder(v: View, viewType: Int): BaseHolder<User> = UserItemHolder(v)

    override fun getLayoutId(viewType: Int): Int = R.layout.recycle_list
}
