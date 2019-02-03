package com.cgmaybe.kotlinfreewan.widget.recyclerview

import android.support.v7.util.DiffUtil
import com.cgmaybe.kotlinfreewan.data.bean.SystemItemDetail

/**
 * recyclerView的diffUtil刷新工具
 */
class RVDiffUtilCallback(
    private val mOldData: List<SystemItemDetail>,
    private val mNewData: List<SystemItemDetail>
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