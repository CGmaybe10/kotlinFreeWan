package com.cgmaybe.kotlinfreewan.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 获取retrofit单例
 */
object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetConst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}