package com.lishide.gankarms.mvp.model.api.service

import com.lishide.gankarms.mvp.model.entity.GankEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 存放通用的一些 API
 *
 * @author lishide
 * @date 2017/11/09
 */
interface CommonService {
    /**
     * 干货列表
     */
    @GET("api/data/{type}/{pageSize}/{page}")
    fun gank(
            @Path("type") type: String?,
            @Path("pageSize") pageSize: Int,
            @Path("page") page: String
    ): Observable<GankEntity>

    /**
     * 随机获取妹子图片
     */
    @GET("api/random/data/福利/10")
    fun getRandomGirl(): Observable<GankEntity>
}
