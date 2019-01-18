package com.cgmaybe.kotlinfreewan.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.data.bean.SystemItemDetail
import com.cgmaybe.kotlinfreewan.utils.getTimeInterval
import kotlinx.android.synthetic.main.system_item.view.*

/**
 * 体系列表的adapter
 */
class SystemListAdapter(private val mContext: Context, private val mSystemData: MutableList<SystemItemDetail>) :
    RecyclerView.Adapter<SystemListAdapter.SystemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SystemViewHolder {
        return SystemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.system_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mSystemData.size
    }

    override fun onBindViewHolder(holder: SystemViewHolder, position: Int) {
        val data = mSystemData[position]
        holder.systemTitleTV.text = data.title
        holder.systemAuthorTV.text = data.author
        holder.systemDateTimeTV.text = getTimeInterval(data.publishTime, "dd/MM/yy")
    }

    class SystemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val systemTitleTV: TextView = itemView.mSystemTitleTV
        val systemAuthorTV: TextView = itemView.mSystemAuthorTV
        val systemDateTimeTV: TextView = itemView.mSystemDateTV
    }
}