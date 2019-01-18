package com.cgmaybe.kotlinfreewan.presenter.contractinterface

import android.support.v4.app.Fragment
import com.cgmaybe.kotlinfreewan.presenter.presenterinterface.IBasePresenter
import com.cgmaybe.kotlinfreewan.ui.viewinterface.IBaseView

/**
 * 项目主页面的协议类
 */
interface ProjectContract {

    interface IProjectPresenter : IBasePresenter {
        /**
         * 获取分类数据
         */
        fun getProjectCategoryData()
    }

    interface IProjectView : IBaseView {
        /**
         * 更新分类view
         */
        fun updateProjectCategory(categoryTitle: Array<String>, categoryFg: MutableList<Fragment>)
    }
}