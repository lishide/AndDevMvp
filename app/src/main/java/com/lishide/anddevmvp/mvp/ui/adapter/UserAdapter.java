package com.lishide.anddevmvp.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.lishide.anddevmvp.R;
import com.lishide.anddevmvp.mvp.model.entity.User;
import com.lishide.anddevmvp.mvp.ui.holder.UserItemHolder;

import java.util.List;

/**
 * 用户列表适配器：展示 {@link DefaultAdapter} 的用法
 *
 * @author lishide
 * @date 2017/11/09
 */
public class UserAdapter extends DefaultAdapter<User> {
    public UserAdapter(List<User> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<User> getHolder(View v, int viewType) {
        return new UserItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.recycle_list;
    }
}
