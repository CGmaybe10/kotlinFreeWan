package com.cgmaybe.kotlinfreewan.data.bean

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.createTypedArrayList(ItemDetailBean.CREATOR),
        source.readInt(),
        1 == source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(curPage)
        writeTypedList(datas)
        writeInt(offset)
        writeInt((if (over) 1 else 0))
        writeInt(pageCount)
        writeInt(size)
        writeInt(total)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SystemDetailBean> = object : Parcelable.Creator<SystemDetailBean> {
            override fun createFromParcel(source: Parcel): SystemDetailBean = SystemDetailBean(source)
            override fun newArray(size: Int): Array<SystemDetailBean?> = arrayOfNulls(size)
        }
    }
}