package com.lishide.anddevmvp.mvp.contract

import android.app.Activity

import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import com.lishide.anddevmvp.mvp.model.entity.User
import com.tbruyelle.rxpermissions2.RxPermissions

import io.reactivex.Observable

/**
 * 展示 Contract 的用法
 *
 * @author lishide
 * @date 2017/11/09
 * @see [Contract wiki 官方文档](https://github.com/JessYanCoding/MVPArms/wiki.2.4.1)
 */
interface UserContract {
    /**
     * 对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
     */
    interface View : IView {

        fun getActivity(): Activity
        /**
         * 申请权限
         */
        fun getRxPermissions(): RxPermissions

        fun startLoadMore()

        fun endLoadMore()
    }

    /**
     * Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
     */
    interface Model : IModel {
        fun getUsers(lastIdQueried: Int, update: Boolean): Observable<List<User>>
    }
}
