package com.cgmaybe.kotlinfreewan.presenter

import android.util.Log
import com.cgmaybe.kotlinfreewan.data.bean.SystemItemDetail
import com.cgmaybe.kotlinfreewan.data.remote.ApiService
import com.cgmaybe.kotlinfreewan.data.remote.RetrofitHelper
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.SystemListContract
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SystemListPresenter(val mSystemListView: SystemListContract.ISystemListView) :
    SystemListContract.ISystemListPresenter {

    private var mSystemPage = 0
    private val mSystemCategoryData = arrayListOf<SystemItemDetail>()

    override fun requestSystemListData(categoryId: Int, refresh: Boolean) {
        if (refresh) {
            mSystemCategoryData.clear()
            mSystemPage = 0
        } else {
            mSystemPage++
        }
        Log.d("moubiao", "page $mSystemPage id = $categoryId")
        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        apiService
            .getSystemListData(mSystemPage, categoryId)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {

            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap { result ->
                Observable.fromIterable(result.data.datas)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<SystemItemDetail> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(result: SystemItemDetail) {
                    mSystemCategoryData.add(result)
                }

                override fun onComplete() {
                    mSystemListView.updateSystemListData(refresh)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    fun getListData(): MutableList<SystemItemDetail> {
        return mSystemCategoryData
    }
}