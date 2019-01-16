package com.cgmaybe.kotlinfreewan.data.bean

/**
 * 首页常用网站
 */
data class HomeWebsiteBean(
    val icon: String,
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)