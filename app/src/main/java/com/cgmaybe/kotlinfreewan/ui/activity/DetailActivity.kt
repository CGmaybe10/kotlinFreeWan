package com.cgmaybe.kotlinfreewan.ui.activity

import android.os.Bundle
import com.cgmaybe.kotlinfreewan.R
import kotlinx.android.synthetic.main.common_detail_layout.*

class DetailActivity : BaseActivity() {
    private lateinit var mWebUrl: String

    override fun getLayoutId(): Int = R.layout.common_detail_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setListener()
    }

    private fun initView() {
        mDetailTitleTV.text = intent.getStringExtra(COMMON_DETAIL_TITLE)
        mWebUrl = intent.getStringExtra(COMMON_DETAIL_URL)
        mDetailWV.loadUrl(mWebUrl)
    }

    private fun setListener() {
        mDetailBackIMG.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val COMMON_DETAIL_TITLE = "commonDetailTitle"
        const val COMMON_DETAIL_URL = "commonDetailUrl"
    }
}