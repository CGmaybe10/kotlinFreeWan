package com.cgmaybe.kotlinfreewan.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.presenter.ProjectListPresenter
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.ProjectListContract
import com.cgmaybe.kotlinfreewan.ui.activity.DetailActivity
import com.cgmaybe.kotlinfreewan.ui.adapter.ProjectListAdapter
import kotlinx.android.synthetic.main.project_list_layout.*

/**
 * 项目列表页面的fragment
 */
class ProjectListFragment : Fragment(), ProjectListContract.IProjectListView {
    private lateinit var mProjectListPresenter: ProjectListPresenter
    private lateinit var mProjectListAdapter: ProjectListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mProjectListPresenter = ProjectListPresenter(this)
        mProjectListAdapter = ProjectListAdapter(activity!!, mProjectListPresenter.getListData())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.project_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mProjectRV.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        mProjectRV.adapter = mProjectListAdapter

        mProjectListPresenter.getCategoryListData(arguments?.getInt(PROJECT_CATEGORY_ID) ?: 0, true)
        setListener()
    }

    private fun setListener() {
        mProjectSRL.setOnRefreshListener {
            mProjectListPresenter.getCategoryListData(arguments?.getInt(PROJECT_CATEGORY_ID) ?: 0, true)
        }

        mProjectSRL.setOnLoadMoreListener {
            mProjectListPresenter.getCategoryListData(arguments?.getInt(PROJECT_CATEGORY_ID) ?: 0, false)
        }

        mProjectListAdapter.setItemClickListener { _, position: Int ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.COMMON_DETAIL_TITLE, mProjectListPresenter.getListData()[position].title)
            intent.putExtra(DetailActivity.COMMON_DETAIL_URL, mProjectListPresenter.getListData()[position].link)
            startActivity(intent)
        }
    }

    override fun updateCategoryList(refresh: Boolean) {
        if (refresh) {
            mProjectSRL?.finishRefresh()
        } else {
            mProjectSRL?.finishLoadMore()
        }
        mProjectListAdapter.notifyDataSetChanged()
    }

    companion object {
        const val PROJECT_CATEGORY_ID = "projectCategoryID"//项目分类的ID
    }
}