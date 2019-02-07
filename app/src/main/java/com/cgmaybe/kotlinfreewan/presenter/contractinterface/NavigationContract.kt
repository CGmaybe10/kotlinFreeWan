package com.cgmaybe.kotlinfreewan.presenter.contractinterface

import com.cgmaybe.kotlinfreewan.presenter.presenterinterface.IBasePresenter
import com.cgmaybe.kotlinfreewan.ui.viewinterface.IBaseView

interface NavigationContract {
    interface INavigationPresenter : IBasePresenter {
        /**
         * 获取分类数据
         */
        fun getNavigationData()
    }

    interface INavigationView : IBaseView {
        /**
         * 更新分类view
         */
        fun updateNavigationUi()
    }
}