package com.cgmaybe.kotlinfreewan.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.data.bean.ItemDetailBean
import com.cgmaybe.kotlinfreewan.utils.getTimeInterval
import kotlinx.android.synthetic.main.project_item.view.*

/**
 * 工程列表
 */
class ProjectListAdapter(private val mContext: Context, private val mProjectData: MutableList<ItemDetailBean>) :
    RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ProjectListAdapter.ProjectViewHolder {
        return ProjectViewHolder(LayoutInflater.from(mContext).inflate(R.layout.project_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mProjectData.size
    }

    override fun onBindViewHolder(viewHolder: ProjectListAdapter.ProjectViewHolder, position: Int) {
        val detailData = mProjectData[position]
        Glide.with(mContext).load(detailData.envelopePic).into(viewHolder.projectIMG)
        viewHolder.projectTitleTV.text = detailData.title
        viewHolder.projectDescTV.text = detailData.desc
        viewHolder.projectAuthorTV.text = detailData.author
        viewHolder.projectDateTV.text = getTimeInterval(detailData.publishTime, "dd/MM/yy")
    }

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val projectIMG: ImageView = itemView.mProjectIMG
        val projectTitleTV: TextView = itemView.mProjectTitleTV
        val projectDescTV: TextView = itemView.mProjectDescTV
        val projectAuthorTV: TextView = itemView.mProjectAuthorTV
        val projectDateTV: TextView = itemView.mProjectDateTV
    }
}