package com.lishide.gankarms.mvp.model.api.service

import com.lishide.gankarms.mvp.model.entity.GankEntity
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * 存放通用的一些 API
 *
 * @author lishide
 * @date 2017/11/09
 */
interface CommonService {
    /**
     * 随机获取妹子图片
     */
    @GET("api/random/data/福利/10")
    fun getRandomGirl(): Observable<GankEntity>
}
