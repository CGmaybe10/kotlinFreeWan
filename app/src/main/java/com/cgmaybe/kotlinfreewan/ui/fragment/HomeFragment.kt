package com.cgmaybe.kotlinfreewan.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.presenter.HomePresenter
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.HomeContract
import com.cgmaybe.kotlinfreewan.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.home_layout.*

/**
 * 首页fragment
 */
class HomeFragment : Fragment(), HomeContract.HomeView {
    private lateinit var mHomePresenter: HomePresenter
    private lateinit var mHomeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
    }

    private fun initData() {
        mHomePresenter = HomePresenter(this)
        mHomeAdapter = HomeAdapter(activity as Context, mHomePresenter.getHomeData())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mHomeRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mHomeRV.adapter = mHomeAdapter
        mHomePresenter.refreshData()

        setListener()
    }

    private fun setListener() {
        mHomeSRL.setOnRefreshListener {
            mHomePresenter.refreshData()
        }
        mHomeSRL.setOnLoadMoreListener {
            mHomePresenter.loadMoreData()
        }
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