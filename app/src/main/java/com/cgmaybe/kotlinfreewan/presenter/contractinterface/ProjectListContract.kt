package com.cgmaybe.kotlinfreewan.presenter.contractinterface

import com.cgmaybe.kotlinfreewan.presenter.presenterinterface.IBasePresenter
import com.cgmaybe.kotlinfreewan.ui.viewinterface.IBaseView

/**
 * 项目列表
 */
interface ProjectListContract {

    interface IProjectListPresenter: IBasePresenter {
        /**
         * 获取某个分类的列表数据
         */
        fun getCategoryListData(categoryId: Int, refresh: Boolean)
    }

    interface IProjectListView: IBaseView {
        /**
         * 更新某个分类的列表
         */
        fun updateCategoryList(refresh: Boolean)
    }
}