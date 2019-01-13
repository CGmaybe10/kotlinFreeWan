package com.cgmaybe.kotlinfreewan.ui.activity

import android.os.Bundle
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.MainContract

class MainActivity : BaseActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        initView()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private fun initData() {

    }

    private fun initView() {

    }
}
