package com.cgmaybe.kotlinfreewan.data.bean

/**
 * 首页的banner
 */
data class HomeBannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)