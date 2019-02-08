package com.cgmaybe.kotlinfreewan.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.presenter.SystemPresenter
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.SystemContract
import com.cgmaybe.kotlinfreewan.ui.adapter.SystemAdapter
import kotlinx.android.synthetic.main.system_layout.*

/**
 * 体系的fragment
 */
class SystemFragment : Fragment(), SystemContract.ISystemCategoryView {
    private lateinit var mSystemPresenter: SystemPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSystemPresenter = SystemPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.system_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSystemPresenter.getSystemCategoryData()
    }

    override fun updateSystemCategory(categoryTitle: Array<String>, topCateFg: MutableList<Fragment>) {
        mSystemVP.offscreenPageLimit = 2
        mSystemVP.adapter = SystemAdapter(fragmentManager!!, topCateFg)
        mSystemSTL.setViewPager(mSystemVP, categoryTitle)
    }

    companion object {
        const val SYSTEM_CATEGORY_ID = "systemCategoryID"//体系分类的ID
        const val SYSTEM_SUBCATEGORY_ID = "systemSubCategoryID"//体系分类的数据
    }
}