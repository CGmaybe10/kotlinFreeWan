package com.cgmaybe.kotlinfreewan.widget.recyclerview

import android.support.v7.util.DiffUtil
import com.cgmaybe.kotlinfreewan.data.bean.ItemDetailBean

/**
 * recyclerView的diffUtil刷新工具
 * 参考：http://www.10tiao.com/html/169/201610/2650821170/1.html
 */
class RVDiffUtilCallback(
    private val mOldData: List<ItemDetailBean>,
    private val mNewData: List<ItemDetailBean>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldData.size
    }

    override fun getNewListSize(): Int {
        return mNewData.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return mOldData[oldPosition].id == mNewData[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return mOldData[oldPosition].id == mNewData[newPosition].id
    }
}