package com.cgmaybe.kotlinfreewan.presenter

import com.cgmaybe.kotlinfreewan.data.bean.ItemDetailBean
import com.cgmaybe.kotlinfreewan.data.bean.NavigationBean
import com.cgmaybe.kotlinfreewan.data.bean.NavigationEntity
import com.cgmaybe.kotlinfreewan.data.remote.ApiService
import com.cgmaybe.kotlinfreewan.data.remote.RetrofitHelper
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.NavigationContract
import com.cgmaybe.kotlinfreewan.ui.adapter.NavigationContentAdapter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NavigationPresenter(
    private val mNavigationView: NavigationContract.INavigationView,
    val mNaIndicatorData: MutableList<NavigationBean>, val mNaFinalData: MutableList<NavigationEntity>
) :
    NavigationContract.INavigationPresenter {

    override fun getNavigationData() {
        var groupIndex = -1
        var lastGroupId = -1
        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        apiService.getNavigationData()
            .map { indicatorData ->
                mNaIndicatorData.addAll(indicatorData.data)
                return@map mNaIndicatorData
            }
            .flatMap { categoryData ->
                Observable.fromIterable(categoryData)
            }
            .flatMap { detailData ->
                Observable.fromIterable(detailData.articles)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ItemDetailBean> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(detail: ItemDetailBean) {
                    if (lastGroupId != detail.chapterId) {
                        groupIndex++
                        mNaFinalData.add(
                            NavigationEntity(
                                groupIndex,
                                detail.chapterId,
                                NavigationContentAdapter.GROUP_TYPE,
                                detail.chapterName, detail
                            )
                        )
                    }
                    mNaFinalData.add(
                        NavigationEntity(
                            groupIndex,
                            detail.chapterId,
                            NavigationContentAdapter.CHILD_TYPE,
                            detail.chapterName,
                            detail
                        )
                    )
                    lastGroupId = detail.chapterId
                }

                override fun onComplete() {
                    mNavigationView.updateNavigationUi()
                }

                override fun onError(e: Throwable) {

                }
            })
    }
}
