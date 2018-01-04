package com.lishide.anddevmvp.mvp.model.api.service

import com.lishide.anddevmvp.mvp.model.entity.User

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * 展示 [Retrofit.create] 中需要传入的 ApiService 的使用方式
 * 存放关于用户的一些 API
 *
 * @author lishide
 * @date 2017/11/09
 */
interface UserService {

    @Headers(HEADER_API_VERSION)
    @GET("/users")
    fun getUsers(
            @Query("since") lastIdQueried: Int,
            @Query("per_page") perPage: Int
    ): Observable<List<User>>

    companion object {
        const val HEADER_API_VERSION = "Accept: application/vnd.github.v3+json"
    }
}
