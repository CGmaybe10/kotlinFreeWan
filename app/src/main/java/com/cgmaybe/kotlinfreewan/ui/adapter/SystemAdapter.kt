package com.cgmaybe.kotlinfreewan.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SystemAdapter(fg: FragmentManager, private val systemData: List<Fragment>) : FragmentStatePagerAdapter(fg) {

    override fun getItem(position: Int): Fragment {
        return systemData[position]
    }

    override fun getCount(): Int {
        return systemData.size
    }
}