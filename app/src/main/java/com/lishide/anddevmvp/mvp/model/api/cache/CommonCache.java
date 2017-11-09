package com.lishide.anddevmvp.mvp.model.api.cache;

import com.lishide.anddevmvp.mvp.model.entity.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;

/**
 * 展示 {@link RxCache#using(Class)} 中需要传入的 Providers 的使用方式
 *
 * @author lishide
 * @date 2017/11/09
 */
public interface CommonCache {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<User>>> getUsers(Observable<List<User>> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);
}
