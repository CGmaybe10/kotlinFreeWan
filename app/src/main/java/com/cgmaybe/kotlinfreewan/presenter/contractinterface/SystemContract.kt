package com.cgmaybe.kotlinfreewan.presenter.contractinterface

import android.support.v4.app.Fragment
import com.cgmaybe.kotlinfreewan.data.bean.SystemCategoryItemDetail
import com.cgmaybe.kotlinfreewan.presenter.presenterinterface.IBasePresenter
import com.cgmaybe.kotlinfreewan.ui.viewinterface.IBaseView

interface SystemContract {

    interface ISystemCategoryPresenter : IBasePresenter {
        /**
         * 获取分类数据
         */
        fun getSystemCategoryData()
    }

    interface ISystemCategoryView : IBaseView {
        /**
         * 更新分类view
         */
        fun updateSystemCategory(categoryTitle: Array<String>, topCateFg: MutableList<Fragment>)
    }
}