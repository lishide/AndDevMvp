package com.lishide.anddevmvp.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.lishide.anddevmvp.mvp.model.entity.User;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observable;

/**
 * 展示 Contract 的用法
 *
 * @author lishide
 * @date 2017/11/09
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.1">Contract wiki 官方文档</a>
 */
public interface UserContract {
    /**
     * 对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
     */
    interface View extends IView {
        void startLoadMore();

        void endLoadMore();

        Activity getActivity();

        /**
         * 申请权限
         */
        RxPermissions getRxPermissions();
    }

    /**
     * Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
     */
    interface Model extends IModel {
        Observable<List<User>> getUsers(int lastIdQueried, boolean update);
    }
}
