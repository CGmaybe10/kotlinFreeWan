package com.cgmaybe.kotlinfreewan.data.bean

/**
 * 导航数据
 */
data class NavigationBean(
    val articles: List<ItemDetailBean>,
    val cid: Int,
    val name: String
)