package com.cgmaybe.kotlinfreewan.data.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * 导航数据
 */
data class NavigationBean(
    val articles: List<ItemDetailBean>,
    val cid: Int,
    val name: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.createTypedArrayList(ItemDetailBean.CREATOR),
        source.readInt(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(articles)
        writeInt(cid)
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NavigationBean> = object : Parcelable.Creator<NavigationBean> {
            override fun createFromParcel(source: Parcel): NavigationBean = NavigationBean(source)
            override fun newArray(size: Int): Array<NavigationBean?> = arrayOfNulls(size)
        }
    }
}