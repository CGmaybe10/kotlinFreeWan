package com.cgmaybe.kotlinfreewan.data.bean

import android.os.Parcel
import android.os.Parcelable

data class NavigationEntity(
    val mGroupIndex: Int,
    val mGroupId: Int,
    val mGroupType: Int,
    val mGroupName: String,
    val mChild: ItemDetailBean
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readString(),
        source.readParcelable<ItemDetailBean>(ItemDetailBean::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(mGroupIndex)
        writeInt(mGroupId)
        writeInt(mGroupType)
        writeString(mGroupName)
        writeParcelable(mChild, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NavigationEntity> = object : Parcelable.Creator<NavigationEntity> {
            override fun createFromParcel(source: Parcel): NavigationEntity = NavigationEntity(source)
            override fun newArray(size: Int): Array<NavigationEntity?> = arrayOfNulls(size)
        }
    }
}