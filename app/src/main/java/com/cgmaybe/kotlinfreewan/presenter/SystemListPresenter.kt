package com.cgmaybe.kotlinfreewan.presenter

import android.support.v7.util.DiffUtil
import android.util.Log
import com.cgmaybe.kotlinfreewan.data.bean.SystemItemDetail
import com.cgmaybe.kotlinfreewan.data.remote.ApiService
import com.cgmaybe.kotlinfreewan.data.remote.RetrofitHelper
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.SystemListContract
import com.cgmaybe.kotlinfreewan.widget.recyclerview.RVDiffUtilCallback
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SystemListPresenter(val mSystemListView: SystemListContract.ISystemListView) :
    SystemListContract.ISystemListPresenter {

    private var mSystemPage = 0
    private val mSystemCategoryData = arrayListOf<SystemItemDetail>()

    override fun requestSystemListData(categoryId: Int, refresh: Boolean) {
        val newData = arrayListOf<SystemItemDetail>()
        if (refresh) {
            mSystemPage = 0
        } else {
            mSystemPage++
            newData.addAll(mSystemCategoryData)
        }
        Log.d("moubiao", "page $mSystemPage id = $categoryId")
        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        apiService
            .getSystemListData(mSystemPage, categoryId)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {

            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .map { result ->
                result.data.datas
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<SystemItemDetail>> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(result: List<SystemItemDetail>) {
                    newData.addAll(result)
                    val diffCallback = RVDiffUtilCallback(mSystemCategoryData, newData)
                    val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
                    mSystemCategoryData.clear()
                    mSystemCategoryData.addAll(newData)
                    mSystemListView.updateSystemListData(refresh, diffResult)
                }

                override fun onComplete() {
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