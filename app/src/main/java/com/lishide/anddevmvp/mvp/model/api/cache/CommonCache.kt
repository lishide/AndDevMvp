package com.lishide.anddevmvp.mvp.model.api.cache

import com.lishide.anddevmvp.mvp.model.entity.User
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider
import io.rx_cache2.LifeCache
import io.rx_cache2.Reply
import io.rx_cache2.internal.RxCache
import java.util.concurrent.TimeUnit

/**
 * 展示 [RxCache.using] 中需要传入的 Providers 的使用方式
 *
 * @author lishide
 * @date 2017/11/09
 */
interface CommonCache {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    fun getUsers(
            users: Observable<List<User>>,
            idLastUserQueried: DynamicKey,
            evictProvider: EvictProvider
    ): Observable<Reply<List<User>>>
}
