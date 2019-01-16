package com.cgmaybe.kotlinfreewan.data.bean

/**
 * 数据的基本结构
 */
data class BaseResult<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)