package com.cgmaybe.kotlinfreewan.data.bean

/**
 * 数据的基本结构
 */
data class BaseResultBean<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)