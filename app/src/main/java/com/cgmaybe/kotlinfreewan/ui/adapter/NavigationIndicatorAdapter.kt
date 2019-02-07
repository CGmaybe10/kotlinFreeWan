package com.cgmaybe.kotlinfreewan.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.data.bean.NavigationBean
import com.cgmaybe.kotlinfreewan.widget.recyclerview.interfaces.ItemClick
import kotlinx.android.synthetic.main.navigation_indicator_item.view.*


/**
 * 导航页面左边指示器
 */
class NavigationIndicatorAdapter(
    private val mContext: Context,
    private val mNaIndicatorData: MutableList<NavigationBean>
) : RecyclerView.Adapter<NavigationIndicatorAdapter.NaIndicatorVH>() {

    private var mSelectedGroupId = -1
    private var mOnRvItemClickListener: ItemClick? = null

    init {
        if (mNaIndicatorData.size > 0) {
            mSelectedGroupId = mNaIndicatorData[0].cid
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaIndicatorVH {
        val inflater = LayoutInflater.from(mContext)
        return NaIndicatorVH(inflater.inflate(R.layout.navigation_indicator_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mNaIndicatorData.size
    }

    override fun onBindViewHolder(viewHolder: NaIndicatorVH, position: Int) {
        val indicatorData: NavigationBean = mNaIndicatorData[position]
        viewHolder.naIndicatorTitleTV.text = indicatorData.name
        if (mSelectedGroupId == indicatorData.cid) {
            viewHolder.naIndicatorTitleTV.setTextColor(mContext.resources.getColor(R.color.na_selected_group_color))
        } else {
            viewHolder.naIndicatorTitleTV.setTextColor(mContext.resources.getColor(R.color.na_unselected_group_color))
        }

        viewHolder.naIndicatorTitleTV.setOnClickListener {
            mOnRvItemClickListener?.invoke(viewHolder.naIndicatorTitleTV, position)
        }
    }

    fun setSelectedGroupId(selectedGroupId: Int) {
        mSelectedGroupId = selectedGroupId
    }

    fun getSelectedGroupId(): Int {
        return mSelectedGroupId
    }

    fun setOnItemClickListener(onRvItemClickListener: ItemClick) {
        mOnRvItemClickListener = onRvItemClickListener
    }

    class NaIndicatorVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val naIndicatorTitleTV: TextView = itemView.mNaIndicatorTV
    }
}