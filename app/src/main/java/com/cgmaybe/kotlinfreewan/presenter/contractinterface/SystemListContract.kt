package com.cgmaybe.kotlinfreewan.presenter.contractinterface

import android.support.v7.util.DiffUtil
import com.cgmaybe.kotlinfreewan.presenter.presenterinterface.IBasePresenter
import com.cgmaybe.kotlinfreewan.ui.viewinterface.IBaseView

interface SystemListContract {
    interface ISystemListPresenter : IBasePresenter {
        fun requestSystemListData(categoryId: Int, refresh: Boolean)
    }

    interface ISystemListView : IBaseView {
        fun updateSystemListData(refresh: Boolean, diffResult: DiffUtil.DiffResult)
    }
}