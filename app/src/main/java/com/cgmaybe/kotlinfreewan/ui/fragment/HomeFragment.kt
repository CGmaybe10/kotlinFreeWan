package com.cgmaybe.kotlinfreewan.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.data.bean.HomeDataBean
import com.cgmaybe.kotlinfreewan.presenter.HomePresenter
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.HomeContract
import com.cgmaybe.kotlinfreewan.ui.activity.DetailActivity
import com.cgmaybe.kotlinfreewan.ui.activity.DetailActivity.Companion.COMMON_DETAIL_TITLE
import com.cgmaybe.kotlinfreewan.ui.activity.DetailActivity.Companion.COMMON_DETAIL_URL
import com.cgmaybe.kotlinfreewan.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.home_layout.*

/**
 * 首页fragment
 */
class HomeFragment : LazyFragment<HomeDataBean>(), HomeContract.HomeView {
    private lateinit var mHomePresenter: HomePresenter
    private lateinit var mHomeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
    }

    private fun initData() {
        mHomePresenter = HomePresenter(this, mData)
        mHomeAdapter = HomeAdapter(activity as Context, mData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mHomeRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mHomeRV.adapter = mHomeAdapter
        setListener()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setListener() {
        mHomeSRL.setOnRefreshListener {
            mHomePresenter.refreshData()
        }
        mHomeSRL.setOnLoadMoreListener {
            mHomePresenter.loadMoreData()
        }

        mHomeAdapter.setItemClickListener { _, position: Int ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(COMMON_DETAIL_TITLE, mData[position].mItemBean?.title)
            intent.putExtra(COMMON_DETAIL_URL, mData[position].mItemBean?.link)
            startActivity(intent)
        }
    }

    override fun loadData() {
        mHomePresenter.refreshData()
    }

    /**
     * 刷新完成
     */
    override fun completeRefresh() {
        mHomeSRL?.finishRefresh()
        mHomeAdapter.notifyDataSetChanged()
    }

    /**
     * 加载更多完成
     */
    override fun completeLoadMore() {
        mHomeSRL.finishLoadMore()
        mHomeAdapter.notifyDataSetChanged()
    }
}