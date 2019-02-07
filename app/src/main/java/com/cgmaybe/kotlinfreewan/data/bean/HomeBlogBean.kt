package com.cgmaybe.kotlinfreewan.data.bean

import com.google.gson.annotations.SerializedName

data class HomeBlogBean(
    val curPage: Int,
    @SerializedName("datas") val blogListBean: List<ItemDetailBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)