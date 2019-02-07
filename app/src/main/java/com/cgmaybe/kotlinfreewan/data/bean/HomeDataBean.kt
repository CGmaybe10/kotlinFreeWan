package com.cgmaybe.kotlinfreewan.data.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * 首页列表的数据
 */
data class HomeDataBean(val mHomeType: Int, val mHomeBanner: List<HomeBannerBean>?, val mItemBean: ItemDetailBean?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createTypedArrayList(HomeBannerBean.CREATOR),
        parcel.readParcelable(ItemDetailBean::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mHomeType)
        parcel.writeTypedList(mHomeBanner)
        parcel.writeParcelable(mItemBean, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeDataBean> {
        override fun createFromParcel(parcel: Parcel): HomeDataBean {
            return HomeDataBean(parcel)
        }

        override fun newArray(size: Int): Array<HomeDataBean?> {
            return arrayOfNulls(size)
        }
    }
}