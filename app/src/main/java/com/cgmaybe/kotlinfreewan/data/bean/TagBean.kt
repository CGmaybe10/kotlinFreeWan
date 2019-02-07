package com.cgmaybe.kotlinfreewan.data.bean

import android.os.Parcel
import android.os.Parcelable

data class TagBean(
    val name: String,
    val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TagBean> {
        override fun createFromParcel(parcel: Parcel): TagBean {
            return TagBean(parcel)
        }

        override fun newArray(size: Int): Array<TagBean?> {
            return arrayOfNulls(size)
        }
    }
}