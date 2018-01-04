package com.lishide.anddevmvp.mvp.model

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.lishide.anddevmvp.mvp.contract.UserContract
import com.lishide.anddevmvp.mvp.model.api.cache.CommonCache
import com.lishide.anddevmvp.mvp.model.api.service.UserService
import com.lishide.anddevmvp.mvp.model.entity.User
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictDynamicKey
import timber.log.Timber
import javax.inject.Inject

/**
 * 展示 Model 的用法
 *
 * @author lishide
 * @date 2017/11/09
 * @see [Model wiki 官方文档](https://github.com/JessYanCoding/MVPArms/wiki.2.4.3)
 */
@ActivityScope
class UserModel @Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), UserContract.Model {

    override fun getUsers(lastIdQueried: Int, update: Boolean): Observable<List<User>> {
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(UserService::class.java)
                .getUsers(lastIdQueried, USERS_PER_PAGE))
                .flatMap { listObservable ->
                    mRepositoryManager.obtainCacheService(CommonCache::class.java)
                            .getUsers(listObservable, DynamicKey(lastIdQueried), EvictDynamicKey(update))
                            .map { listReply -> listReply.data }
                }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    internal fun onPause() {
        Timber.d("Release Resource")
    }

    companion object {
        val USERS_PER_PAGE = 10
    }

}
