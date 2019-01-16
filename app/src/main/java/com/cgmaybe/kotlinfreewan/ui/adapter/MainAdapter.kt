package com.cgmaybe.kotlinfreewan.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MainAdapter(fragmentManager: FragmentManager, private val mMainData: List<Fragment>) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return mMainData[position]
    }

    override fun getCount(): Int {
        return mMainData.size
    }
}