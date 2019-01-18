package com.cgmaybe.kotlinfreewan.presenter

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cgmaybe.kotlinfreewan.data.bean.SystemCategoryDetail
import com.cgmaybe.kotlinfreewan.data.remote.ApiService
import com.cgmaybe.kotlinfreewan.data.remote.RetrofitHelper
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.SystemContract
import com.cgmaybe.kotlinfreewan.ui.fragment.ProjectListFragment
import com.cgmaybe.kotlinfreewan.ui.fragment.SystemFragment
import com.cgmaybe.kotlinfreewan.ui.fragment.SystemFragment.Companion.SYSTEM_CATEGORY_ID
import com.cgmaybe.kotlinfreewan.ui.fragment.SystemFragment.Companion.SYSTEM_SUBCATEGORY_ID
import com.cgmaybe.kotlinfreewan.ui.fragment.SystemSubCateFragment
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SystemPresenter(private val mSysCateView: SystemContract.ISystemCategoryView) :
    SystemContract.ISystemCategoryPresenter {
    private var mSysCateTitle = arrayListOf<String>()
    private var mSysCateFg = arrayListOf<Fragment>()

    override fun getSystemCategoryData() {
        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        apiService
            .getSystemCategoryData()
            .flatMap {
                Observable.fromIterable(it.data)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<SystemCategoryDetail> {

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(result: SystemCategoryDetail) {
                    mSysCateTitle.add(result.name)
                    val systemCategoryFg = SystemSubCateFragment()//二级分类的fragment
                    val bundle = Bundle()
                    bundle.putString(SYSTEM_SUBCATEGORY_ID, Gson().toJson(result.children))
                    systemCategoryFg.arguments = bundle
                    mSysCateFg.add(systemCategoryFg)
                }

                override fun onComplete() {
                    mSysCateView.updateSystemCategory(mSysCateTitle.toTypedArray(), mSysCateFg)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

}