package com.cgmaybe.kotlinfreewan.data.bean

/**
 * 体系和项目的某个分类的列表数据
 */
data class SystemDetailBean(
    val curPage: Int,
    val datas: List<ItemDetailBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)