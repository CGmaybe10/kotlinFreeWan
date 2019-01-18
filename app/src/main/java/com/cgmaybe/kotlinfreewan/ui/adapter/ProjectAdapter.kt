package com.cgmaybe.kotlinfreewan.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ProjectAdapter(fg: FragmentManager, private val projectData: List<Fragment>) : FragmentStatePagerAdapter(fg) {

    override fun getItem(position: Int): Fragment {
        return projectData[position]
    }

    override fun getCount(): Int {
        return projectData.size
    }
}