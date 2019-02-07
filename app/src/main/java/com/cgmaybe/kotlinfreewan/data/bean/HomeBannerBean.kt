package com.cgmaybe.kotlinfreewan.data.bean

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt(),
        source.readString(),
        source.readInt(),
        source.readInt(),
        source.readString(),
        source.readInt(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(desc)
        writeInt(id)
        writeString(imagePath)
        writeInt(isVisible)
        writeInt(order)
        writeString(title)
        writeInt(type)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<HomeBannerBean> = object : Parcelable.Creator<HomeBannerBean> {
            override fun createFromParcel(source: Parcel): HomeBannerBean = HomeBannerBean(source)
            override fun newArray(size: Int): Array<HomeBannerBean?> = arrayOfNulls(size)
        }
    }
}