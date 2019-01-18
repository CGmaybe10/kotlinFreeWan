package com.cgmaybe.kotlinfreewan.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.presenter.SystemListPresenter
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.SystemListContract
import com.cgmaybe.kotlinfreewan.ui.adapter.SystemListAdapter
import com.cgmaybe.kotlinfreewan.ui.fragment.SystemFragment.Companion.SYSTEM_CATEGORY_ID
import kotlinx.android.synthetic.main.system_list_layout.*

/**
 * 体系的列表fragment
 */
class SystemListFragment : Fragment(), SystemListContract.ISystemListView {
    private lateinit var mSystemListAdapter: SystemListAdapter
    private lateinit var mSystemPresenter: SystemListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSystemPresenter = SystemListPresenter(this)
        mSystemListAdapter = SystemListAdapter(activity as Context, mSystemPresenter.getListData())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.system_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSystemListRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mSystemListRV.adapter = mSystemListAdapter

        setListener()
        mSystemPresenter.requestSystemListData(arguments?.getInt(SYSTEM_CATEGORY_ID) ?: 0, true)
    }

    private fun setListener() {
        mSystemListSRL.setOnRefreshListener {
            mSystemPresenter.requestSystemListData(
                arguments?.getInt(SYSTEM_CATEGORY_ID) ?: 0,
                true
            )
        }
        mSystemListSRL.setOnLoadMoreListener {
            mSystemPresenter.requestSystemListData(
                arguments?.getInt(SYSTEM_CATEGORY_ID) ?: 0,
                false
            )
        }
    }

    override fun updateSystemListData(refresh: Boolean) {
        if (refresh) {
            mSystemListSRL?.finishRefresh()
        } else {
            mSystemListSRL?.finishLoadMore()
        }
        mSystemListAdapter.notifyDataSetChanged()
    }
}