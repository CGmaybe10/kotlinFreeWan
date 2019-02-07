package com.cgmaybe.kotlinfreewan.data.bean

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(icon)
        writeInt(id)
        writeString(link)
        writeString(name)
        writeInt(order)
        writeInt(visible)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<HomeWebsiteBean> = object : Parcelable.Creator<HomeWebsiteBean> {
            override fun createFromParcel(source: Parcel): HomeWebsiteBean = HomeWebsiteBean(source)
            override fun newArray(size: Int): Array<HomeWebsiteBean?> = arrayOfNulls(size)
        }
    }
}