package com.cgmaybe.kotlinfreewan.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.data.bean.HomeDataBean
import com.cgmaybe.kotlinfreewan.ui.activity.DetailActivity
import com.cgmaybe.kotlinfreewan.utils.getTimeInterval
import com.cgmaybe.kotlinfreewan.widget.recyclerview.interfaces.ItemClick
import com.cgmaybe.kotlinfreewan.widget.youthbanner.BannerImageLoader
import com.orhanobut.logger.Logger
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.home_banner_item.view.*
import kotlinx.android.synthetic.main.home_blog_item.view.*
import kotlinx.android.synthetic.main.home_tool_item.view.*

/**
 * 首页列表的adapter
 */
class HomeAdapter(private val mContext: Context, private val mHomeData: List<HomeDataBean>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mHomeItemClickListener: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (mHomeData[viewType].mHomeType) {
            HOME_BANNER_TYPE -> {//banner
                HomeBannerHolder(LayoutInflater.from(mContext).inflate(R.layout.home_banner_item, parent, false))
            }
            HOME_AREA_TYPE -> {//常用专区
                HomeToolHolder(LayoutInflater.from(mContext).inflate(R.layout.home_tool_item, parent, false))
            }
            else -> {//博客
                HomeBlogHolder(LayoutInflater.from(mContext).inflate(R.layout.home_blog_item, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return mHomeData.size
    }

    override fun getItemViewType(position: Int): Int {
        return mHomeData[position].mHomeType
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (mHomeData[position].mHomeType) {
            HOME_BANNER_TYPE -> {//banner
                val bannerHolder = viewHolder as HomeBannerHolder
                val imageData = arrayListOf<String>()
                val titleData = arrayListOf<String>()
                for (banner in mHomeData[position].mHomeBanner!!) {
                    imageData.add(banner.imagePath)
                    titleData.add(banner.title)
                }
                bannerHolder.homeBannerView.setImages(imageData)
                bannerHolder.homeBannerView.setBannerTitles(titleData)
                bannerHolder.homeBannerView.start()

                bannerHolder.homeBannerView.setOnBannerListener { bannerPosition ->
                    val intent = Intent(mContext, DetailActivity::class.java)
                    intent.putExtra(
                        DetailActivity.COMMON_DETAIL_TITLE,
                        mHomeData[position].mHomeBanner?.get(bannerPosition)?.title
                    )
                    intent.putExtra(
                        DetailActivity.COMMON_DETAIL_URL,
                        mHomeData[position].mHomeBanner?.get(bannerPosition)?.url
                    )
                    mContext.startActivity(intent)
                }
            }
            HOME_AREA_TYPE -> {//常用专区
                val toolHolder = viewHolder as HomeToolHolder
                toolHolder.homeToolTV.setOnClickListener {
                    Logger.d("tool holder click---->")
                }
                toolHolder.homeWebsiteTV.setOnClickListener {
                    Logger.d("website holder click---->")
                }
            }
            else -> {//博客
                val blogHolder = viewHolder as HomeBlogHolder
                blogHolder.homeBlogTitleTV.text = mHomeData[position].mItemBean?.title
                blogHolder.homeBlogAuthorTV.text = mHomeData[position].mItemBean?.author
                val category = String.format(
                    mContext.getString(
                        R.string.home_blog_category,
                        mHomeData[position].mItemBean?.superChapterName,
                        mHomeData[position].mItemBean?.chapterName
                    )
                )
                blogHolder.homeBlogCategoryTV.text = category

                blogHolder.homeBlogDateTV.text =
                        getTimeInterval(mHomeData[position].mItemBean!!.publishTime, "dd/MM/yy")

                blogHolder.itemView.setOnClickListener {
                    mHomeItemClickListener?.invoke(blogHolder.itemView, position)
                }
            }

        }
    }

    class HomeBannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homeBannerView: Banner = itemView.mHomeBanner

        init {
            homeBannerView.setImageLoader(BannerImageLoader())
            homeBannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        }
    }

    fun setItemClickListener(clickListener: ItemClick) {
        mHomeItemClickListener = clickListener
    }

    class HomeToolHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homeToolTV: TextView = itemView.mHomeToolTV
        val homeWebsiteTV: TextView = itemView.mHomeWebsiteTV
    }

    class HomeBlogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homeBlogTitleTV: TextView = itemView.mHomeBlogTitleTV
        val homeBlogAuthorTV: TextView = itemView.mHomeBlogAuthorTV
        val homeBlogCategoryTV: TextView = itemView.mHomeBlogCategoryTV
        val homeBlogDateTV: TextView = itemView.mHomeBlogDateTV
    }

    companion object {
        const val HOME_BANNER_TYPE = 0//banner
        const val HOME_AREA_TYPE = 1//常用专区
        const val HOME_BLOG_TYPE = 2//最新博文
    }
}