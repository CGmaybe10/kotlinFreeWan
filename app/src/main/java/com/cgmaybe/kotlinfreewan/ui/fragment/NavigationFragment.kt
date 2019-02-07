package com.cgmaybe.kotlinfreewan.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.*
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.presenter.NavigationPresenter
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.NavigationContract
import com.cgmaybe.kotlinfreewan.ui.activity.DetailActivity
import com.cgmaybe.kotlinfreewan.ui.adapter.NavigationContentAdapter
import com.cgmaybe.kotlinfreewan.ui.adapter.NavigationContentAdapter.Companion.GROUP_TYPE
import com.cgmaybe.kotlinfreewan.ui.adapter.NavigationIndicatorAdapter
import kotlinx.android.synthetic.main.navigation_layout.*

/**
 * 导航页面
 */
class NavigationFragment : Fragment(), NavigationContract.INavigationView {
    private lateinit var mNaPresenter: NavigationPresenter
    private lateinit var mNaIndicatorAdapter: NavigationIndicatorAdapter
    private lateinit var mNaContentAdapter: NavigationContentAdapter
    private lateinit var mIndicatorLM: LinearLayoutManager
    private lateinit var mContentLM: GridLayoutManager
    private var mFromIndicator = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNaPresenter = NavigationPresenter(this)
        mNaIndicatorAdapter = NavigationIndicatorAdapter(activity as Context, mNaPresenter.mNaIndicatorData)
        mNaContentAdapter = NavigationContentAdapter(activity as Context, mNaPresenter.mNaFinalData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.navigation_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mIndicatorLM = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mNaIndicatorRV.layoutManager = mIndicatorLM
        mNaIndicatorRV.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        mNaIndicatorRV.adapter = mNaIndicatorAdapter

        mContentLM = GridLayoutManager(activity, 2)
        mContentLM.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(p0: Int): Int {
                return when (mNaPresenter.mNaFinalData[p0].mGroupType) {
                    GROUP_TYPE -> 2
                    else -> 1
                }
            }
        }
        mNaContentRV.layoutManager = mContentLM
        mNaContentRV.adapter = mNaContentAdapter

        mNaPresenter.getNavigationData()

        setListener()
    }

    private fun setListener() {
        mNaIndicatorAdapter.setOnItemClickListener { _, position: Int ->
            val groupId = mNaPresenter.mNaIndicatorData[position].cid
            for (index in mNaPresenter.mNaFinalData.indices) {
                if (groupId == mNaPresenter.mNaFinalData[index].mGroupId) {
                    mFromIndicator = true

                    mNaIndicatorAdapter.setSelectedGroupId(groupId)
                    mNaIndicatorAdapter.notifyDataSetChanged()

                    mNaContentAdapter.setSelectedGroupId(groupId)
                    mNaContentAdapter.notifyDataSetChanged()

                    val smoothScroller = object : LinearSmoothScroller(activity) {
                        override fun getVerticalSnapPreference(): Int {
                            return LinearSmoothScroller.SNAP_TO_START
                        }
                    }
                    smoothScroller.targetPosition = index
                    mNaContentRV.layoutManager?.startSmoothScroll(smoothScroller)
                    break
                }
            }
        }

        mNaContentRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val curFirstGroup = mNaPresenter.mNaFinalData[mContentLM.findFirstVisibleItemPosition()].mGroupId
                if (!mFromIndicator && curFirstGroup != mNaIndicatorAdapter.getSelectedGroupId()) {
                    mNaIndicatorAdapter.setSelectedGroupId(curFirstGroup)
                    mNaIndicatorAdapter.notifyDataSetChanged()

                    mNaContentAdapter.setSelectedGroupId(curFirstGroup)
                    mNaContentAdapter.notifyDataSetChanged()

                    val groupIndex =
                        mNaPresenter.mNaFinalData[mContentLM.findFirstVisibleItemPosition()].mGroupIndex
                    mIndicatorLM.scrollToPositionWithOffset(groupIndex, 0)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mFromIndicator = false
                    mStickyGroupTV.text =
                            mNaPresenter.mNaFinalData[mContentLM.findFirstVisibleItemPosition()].mGroupName

                    if (mNaPresenter.mNaFinalData[mContentLM.findFirstVisibleItemPosition()].mGroupId == mNaIndicatorAdapter.getSelectedGroupId()) {
                        mStickyGroupTV.setTextColor(resources.getColor(R.color.na_selected_group_color))
                    } else {
                        mStickyGroupTV.setTextColor(resources.getColor(R.color.na_group_color))
                    }
                }
            }
        })

        mNaContentAdapter.setItemClickListener { _, position ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.COMMON_DETAIL_TITLE, mNaPresenter.mNaFinalData[position].mChild.title)
            intent.putExtra(DetailActivity.COMMON_DETAIL_URL, mNaPresenter.mNaFinalData[position].mChild.link)
            startActivity(intent)
        }
    }

    override fun updateNavigationUi() {
        mStickyGroupTV.text = mNaPresenter.mNaIndicatorData[0].name
        mNaIndicatorAdapter.setSelectedGroupId(mNaPresenter.mNaFinalData[0].mGroupId)
        mNaContentAdapter.setSelectedGroupId(mNaPresenter.mNaFinalData[0].mGroupId)
        mNaIndicatorAdapter.notifyDataSetChanged()
        mNaContentAdapter.notifyDataSetChanged()
    }
}