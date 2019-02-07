package com.cgmaybe.kotlinfreewan.data.bean

/**
 * 体系和项目的一级分类和二级分类
 */
data class SystemCategoryDetailBean(
    val children: List<SystemCategoryItemDetailBean>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)