package com.cgmaybe.kotlinfreewan.data.bean

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(source: Parcel) : this(
        source.createTypedArrayList(SystemCategoryItemDetailBean.CREATOR),
        source.readInt(),
        source.readInt(),
        source.readString(),
        source.readInt(),
        source.readInt(),
        1 == source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(children)
        writeInt(courseId)
        writeInt(id)
        writeString(name)
        writeInt(order)
        writeInt(parentChapterId)
        writeInt((if (userControlSetTop) 1 else 0))
        writeInt(visible)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SystemCategoryDetailBean> =
            object : Parcelable.Creator<SystemCategoryDetailBean> {
                override fun createFromParcel(source: Parcel): SystemCategoryDetailBean =
                    SystemCategoryDetailBean(source)

                override fun newArray(size: Int): Array<SystemCategoryDetailBean?> = arrayOfNulls(size)
            }
    }
}