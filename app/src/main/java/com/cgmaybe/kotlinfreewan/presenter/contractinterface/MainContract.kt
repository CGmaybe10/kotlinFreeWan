package com.cgmaybe.kotlinfreewan.presenter.contractinterface

import com.cgmaybe.kotlinfreewan.presenter.presenterinterface.IBasePresenter
import com.cgmaybe.kotlinfreewan.ui.viewinterface.IBaseView

/**
 * 主页面的协议类
 */
interface MainContract {

    /**
     * 主页面的view接口
     */
    interface View : IBaseView {

    }

    /**
     * 主页面的present
     */
    interface Presenter : IBasePresenter {

    }
}