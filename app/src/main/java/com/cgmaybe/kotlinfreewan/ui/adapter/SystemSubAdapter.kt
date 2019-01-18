package com.cgmaybe.kotlinfreewan.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SystemSubAdapter(fg: FragmentManager, private val systemSubData: List<Fragment>) : FragmentStatePagerAdapter(fg) {

    override fun getItem(position: Int): Fragment {
        return systemSubData[position]
    }

    override fun getCount(): Int {
        return systemSubData.size
    }
}