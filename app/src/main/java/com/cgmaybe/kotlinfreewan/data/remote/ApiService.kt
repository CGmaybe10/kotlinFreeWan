package com.cgmaybe.kotlinfreewan.data.remote

import com.cgmaybe.kotlinfreewan.data.bean.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 网络请求的接口
 */
interface ApiService {
    /**
     * 获取首页banner
     */
    @GET("banner/json")
    fun getHomeBanner(): Observable<BaseResultBean<List<HomeBannerBean>>>

    /**
     * 获取首页列表
     */
    @GET("article/list/{page}/json")
    fun getHomeBlog(@Path("page") page: Int): Observable<BaseResultBean<HomeBlogBean>>

    /**
     * 获取首页常用网址
     */
    @GET("friend/json")
    fun getHomeWebsite(): Observable<BaseResultBean<HomeWebsiteBean>>

    /**
     * 获取项目的分类数据
     */
    @GET("tree/json")
    fun getSystemCategoryData(): Observable<BaseResultBean<List<SystemCategoryDetailBean>>>

    /**
     * 获取体系某个分类的数据
     */
    @GET("/article/list/{page}/json")
    fun getSystemListData(@Path("page") page: Int, @Query("cid") categoryID: Int): Observable<BaseResultBean<SystemDetailBean>>

    /**
     * 获取项目的分类数据
     */
    @GET("project/tree/json")
    fun getProjectCategoryData(): Observable<BaseResultBean<List<SystemCategoryDetailBean>>>

    /**
     * 获取项目某个分类的数据
     */
    @GET("project/list/{page}/json")
    fun getCategoryListData(@Path("page") page: Int, @Query("cid") categoryID: Int): Observable<BaseResultBean<SystemDetailBean>>

    @GET("navi/json")
    fun getNavigationData(): Observable<BaseResultBean<List<NavigationBean>>>
}