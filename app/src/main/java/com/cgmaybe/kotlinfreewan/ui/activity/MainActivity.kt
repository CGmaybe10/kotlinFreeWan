package com.cgmaybe.kotlinfreewan.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.MainContract
import com.cgmaybe.kotlinfreewan.ui.adapter.MainAdapter
import com.cgmaybe.kotlinfreewan.ui.fragment.HomeFragment
import com.cgmaybe.kotlinfreewan.ui.fragment.NavigationFragment
import com.cgmaybe.kotlinfreewan.ui.fragment.ProjectFragment
import com.cgmaybe.kotlinfreewan.ui.fragment.SystemFragment
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        initView()
        setListener()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private fun initData() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    private fun initView() {
        mMainBottomNB
            .addItem(BottomNavigationItem(R.drawable.home, getString(R.string.main_home)))
            .addItem(BottomNavigationItem(R.drawable.system, getString(R.string.main_system)))
            .addItem(BottomNavigationItem(R.drawable.project, getString(R.string.main_project)))
            .addItem(BottomNavigationItem(R.drawable.navigation, getString(R.string.main_navigation)))
            .setFirstSelectedPosition(0)
            .setMode(MODE_FIXED)
            .initialise()

        val mainData: MutableList<Fragment> = arrayListOf()
        mainData.add(HomeFragment())
        mainData.add(SystemFragment())
        mainData.add(ProjectFragment())
        mainData.add(NavigationFragment())
        mMainVP.adapter = MainAdapter(supportFragmentManager, mainData)
    }

    private fun setListener() {
        mMainVP.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                mMainBottomNB.setFirstSelectedPosition(p0).initialise()
            }

        })
        mMainBottomNB.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {

            }

            override fun onTabSelected(position: Int) {
                mMainVP.setCurrentItem(position, true)
            }

        })
    }
}
