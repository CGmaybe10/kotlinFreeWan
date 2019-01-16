package com.cgmaybe.kotlinfreewan.data.remote

import com.cgmaybe.kotlinfreewan.data.bean.BaseResult
import com.cgmaybe.kotlinfreewan.data.bean.HomeBannerBean
import com.cgmaybe.kotlinfreewan.data.bean.HomeBlogBean
import com.cgmaybe.kotlinfreewan.data.bean.HomeWebsiteBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 网络请求的接口
 */
interface ApiService {
    /**
     * 获取首页banner
     */
    @GET("banner/json")
    fun getHomeBanner(): Observable<BaseResult<List<HomeBannerBean>>>

    /**
     * 获取首页列表
     */
    @GET("article/list/{page}/json")
    fun getHomeBlog(@Path("page") page: String): Observable<BaseResult<HomeBlogBean>>

    /**
     * 获取首页常用网址
     */
    @GET("friend/json")
    fun getHomeWebsite(): Observable<BaseResult<HomeWebsiteBean>>
}