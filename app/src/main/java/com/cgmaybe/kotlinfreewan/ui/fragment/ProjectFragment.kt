package com.cgmaybe.kotlinfreewan.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.presenter.ProjectPresenter
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.ProjectContract
import com.cgmaybe.kotlinfreewan.ui.adapter.ProjectAdapter
import kotlinx.android.synthetic.main.project_layout.*

/**
 * 工程的主页面
 */
class ProjectFragment : Fragment(), ProjectContract.IProjectView {
    private lateinit var mProjectPresenter: ProjectPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mProjectPresenter = ProjectPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.project_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mProjectPresenter.getProjectCategoryData()
    }

    override fun updateProjectCategory(categoryTitle: Array<String>, categoryFg: MutableList<Fragment>) {
        mProjectVP.offscreenPageLimit = 2
        mProjectVP.adapter = ProjectAdapter(fragmentManager!!, categoryFg)
        mProjectSTL.setViewPager(mProjectVP, categoryTitle)
    }
}