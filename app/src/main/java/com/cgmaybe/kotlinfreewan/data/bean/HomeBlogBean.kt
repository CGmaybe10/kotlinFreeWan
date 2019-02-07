package com.cgmaybe.kotlinfreewan.data.bean

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class HomeBlogBean(
    val curPage: Int,
    @SerializedName("datas") val blogListBean: List<ItemDetailBean>,
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
        writeTypedList(blogListBean)
        writeInt(offset)
        writeInt((if (over) 1 else 0))
        writeInt(pageCount)
        writeInt(size)
        writeInt(total)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<HomeBlogBean> = object : Parcelable.Creator<HomeBlogBean> {
            override fun createFromParcel(source: Parcel): HomeBlogBean = HomeBlogBean(source)
            override fun newArray(size: Int): Array<HomeBlogBean?> = arrayOfNulls(size)
        }
    }
}