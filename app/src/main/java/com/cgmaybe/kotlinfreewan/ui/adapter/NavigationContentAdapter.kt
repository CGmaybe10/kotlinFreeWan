package com.cgmaybe.kotlinfreewan.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.data.bean.NavigationEntity
import com.cgmaybe.kotlinfreewan.widget.recyclerview.interfaces.ItemClick
import kotlinx.android.synthetic.main.navigation_content_item.view.*
import kotlinx.android.synthetic.main.navigation_group_item.view.*

/**
 * 导航页面右边详情
 */
class NavigationContentAdapter(private val mContext: Context, private val mNaFinalData: MutableList<NavigationEntity>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mSelectedGroupId = -1
    private var mNaItemClickListener: ItemClick? = null

    init {
        if (mNaFinalData.size > 0) {
            mSelectedGroupId = mNaFinalData[0].mGroupId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        return when (viewType) {
            GROUP_TYPE -> NaGroupVH(inflater.inflate(R.layout.navigation_group_item, parent, false))
            else -> NaContentVH(inflater.inflate(R.layout.navigation_content_item, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return mNaFinalData.size
    }

    override fun getItemViewType(position: Int): Int {
        return mNaFinalData[position].mGroupType
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val contentData = mNaFinalData[position]
        if (contentData.mGroupType == GROUP_TYPE) {
            viewHolder as NaGroupVH
            viewHolder.naGroupTV.text = contentData.mChild.chapterName
            if (mSelectedGroupId == contentData.mGroupId) {
                viewHolder.naGroupTV.setTextColor(mContext.resources.getColor(R.color.na_selected_group_color))
            } else {
                viewHolder.naGroupTV.setTextColor(mContext.resources.getColor(R.color.na_group_color))
            }
        } else {
            viewHolder as NaContentVH
            viewHolder.naContentTV.text = contentData.mChild.title
            viewHolder.itemView.setOnClickListener {
                mNaItemClickListener?.invoke(viewHolder.itemView, position)
            }
        }
    }

    fun setSelectedGroupId(selectedGroupId: Int) {
        mSelectedGroupId = selectedGroupId
    }

    fun getSelectedGroupId(): Int {
        return mSelectedGroupId
    }

    fun setItemClickListener(clickListener: ItemClick) {
        mNaItemClickListener = clickListener
    }

    class NaGroupVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val naGroupTV: TextView = itemView.mNaGroupTV
    }

    class NaContentVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val naContentTV: TextView = itemView.mNaContentTV
    }

    companion object {
        const val GROUP_TYPE: Int = 0
        const val CHILD_TYPE: Int = 1
    }
}