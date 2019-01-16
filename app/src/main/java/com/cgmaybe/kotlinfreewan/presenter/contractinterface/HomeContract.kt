package com.cgmaybe.kotlinfreewan.presenter.contractinterface

import com.cgmaybe.kotlinfreewan.presenter.presenterinterface.IBasePresenter
import com.cgmaybe.kotlinfreewan.ui.viewinterface.IBaseView

interface HomeContract {
    /**
     * 主页面的view接口
     */
    interface HomeView : IBaseView {
        /**
         * 完成刷新
         */
        fun completeRefresh()

        /**
         * 完成加载更多
         */
        fun completeLoadMore()
    }

    /**
     * 主页面的present
     */
    interface HomePresenter : IBasePresenter {
        /**
         * 刷新数据
         */
        fun refreshData()

        /**
         * 加载更多数据
         */
        fun loadMoreData()
    }
}