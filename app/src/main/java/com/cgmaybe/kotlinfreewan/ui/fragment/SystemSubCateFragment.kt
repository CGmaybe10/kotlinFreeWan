package com.cgmaybe.kotlinfreewan.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.data.bean.SystemCategoryItemDetailBean
import com.cgmaybe.kotlinfreewan.ui.adapter.SystemSubAdapter
import com.cgmaybe.kotlinfreewan.ui.fragment.SystemFragment.Companion.SYSTEM_CATEGORY_ID
import com.cgmaybe.kotlinfreewan.ui.fragment.SystemFragment.Companion.SYSTEM_SUBCATEGORY_ID
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.system_subcategory_layout.*


class SystemSubCateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.system_subcategory_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mSystemSubFg: MutableList<Fragment> = arrayListOf()
        val mSystemSubTitle: MutableList<String> = arrayListOf()

        val childrenStr = arguments?.getString(SYSTEM_SUBCATEGORY_ID)
        val listFgData: List<SystemCategoryItemDetailBean> =
            Gson().fromJson(childrenStr, object : TypeToken<List<SystemCategoryItemDetailBean>>() {}.type)
        for (item in listFgData) {
            val sysListFragment = SystemListFragment()
            val bundle = Bundle()
            bundle.putInt(SYSTEM_CATEGORY_ID, item.id)
            sysListFragment.arguments = bundle
            mSystemSubFg.add(sysListFragment)
            mSystemSubTitle.add(item.name)
        }
        mSystemSubCateVP.adapter = SystemSubAdapter(childFragmentManager, mSystemSubFg)//这里要用childFragmentManager，否则无法显示出来
        mSystemSubCateSTL.setViewPager(mSystemSubCateVP, mSystemSubTitle.toTypedArray())
    }
}