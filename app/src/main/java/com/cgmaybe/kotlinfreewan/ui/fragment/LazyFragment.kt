package com.cgmaybe.kotlinfreewan.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.View
import java.util.*

/**
 * 懒加载的fragment
 */
abstract class LazyFragment<T : Parcelable> : Fragment() {

    private var isFirst = true//是否是第一次进入fragment
    private var isVisibleNow = false//fragment是否可见
    private var isPrepared = false//view是否加载完成

    protected lateinit var mData: ArrayList<T>

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("data", mData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mData = when (savedInstanceState != null) {
            true -> savedInstanceState.getParcelableArrayList("data") ?: ArrayList()
            false -> ArrayList()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        isVisibleNow = isVisibleToUser
        if (isVisibleNow) {
            onVisible()
        } else {
            onInvisible()
        }
    }

    /**
     * 必须在子类初始化完view后使用super调用该方法，否则会出现空指针
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isPrepared = true
        onVisible()
    }

    /**
     * fragment可见时
     */
    private fun onVisible() {
        if (isFirst && isVisibleNow && isPrepared) {
            loadData()
            isFirst = false
        }
    }

    /**
     * fragment不可见时
     */
    protected fun onInvisible() {

    }

    /**
     * 加载数据
     */
    protected abstract fun loadData()
}