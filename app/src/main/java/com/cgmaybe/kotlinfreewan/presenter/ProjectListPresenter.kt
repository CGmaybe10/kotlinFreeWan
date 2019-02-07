package com.cgmaybe.kotlinfreewan.presenter

import com.cgmaybe.kotlinfreewan.data.bean.ItemDetailBean
import com.cgmaybe.kotlinfreewan.data.remote.ApiService
import com.cgmaybe.kotlinfreewan.data.remote.RetrofitHelper
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.ProjectListContract
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectListPresenter(private val mCategoryListView: ProjectListContract.IProjectListView, private val mProjectCategoryData: MutableList<ItemDetailBean>) :
    ProjectListContract.IProjectListPresenter {
    private var mProjectPage = 1

    override fun getCategoryListData(categoryId: Int, refresh: Boolean) {

        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        apiService
            .getCategoryListData(0, categoryId)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                if (refresh) {
                    mProjectCategoryData.clear()
                    mProjectPage = 1
                } else {
                    mProjectPage++
                }
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap { result ->
                Observable.fromIterable(result.data.datas)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ItemDetailBean> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(result: ItemDetailBean) {
                    mProjectCategoryData.add(result)
                }

                override fun onComplete() {
                    mCategoryListView.updateCategoryList(refresh)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }
}