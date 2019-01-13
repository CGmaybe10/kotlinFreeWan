package com.cgmaybe.kotlinfreewan.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * activity的基类
 * kotlin + mvp + dagger2 + retrofit + okhttp + rxjava + glide
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    /**
     * 设置layout的Id
     */
    abstract fun getLayoutId(): Int
}