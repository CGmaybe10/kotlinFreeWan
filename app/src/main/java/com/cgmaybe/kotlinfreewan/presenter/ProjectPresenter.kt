package com.cgmaybe.kotlinfreewan.presenter

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cgmaybe.kotlinfreewan.data.bean.SystemCategoryDetail
import com.cgmaybe.kotlinfreewan.data.remote.ApiService
import com.cgmaybe.kotlinfreewan.data.remote.RetrofitHelper
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.ProjectContract
import com.cgmaybe.kotlinfreewan.ui.fragment.ProjectListFragment
import com.cgmaybe.kotlinfreewan.ui.fragment.ProjectListFragment.Companion.PROJECT_CATEGORY_ID
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectPresenter(private val mProjectCategoryView: ProjectContract.IProjectView) :
    ProjectContract.IProjectPresenter {
    private var mCategoryTitle = arrayListOf<String>()
    private var mCategoryFg = arrayListOf<Fragment>()

    override fun getProjectCategoryData() {
        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        apiService
            .getProjectCategoryData()
            .flatMap { result ->
                Observable.fromIterable(result.data)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<SystemCategoryDetail> {

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(result: SystemCategoryDetail) {
                    mCategoryTitle.add(result.name)
                    val projectListFg = ProjectListFragment()
                    val bundle = Bundle()
                    bundle.putInt(PROJECT_CATEGORY_ID, result.id)
                    projectListFg.arguments = bundle
                    mCategoryFg.add(projectListFg)
                }

                override fun onComplete() {
                    mProjectCategoryView.updateProjectCategory(mCategoryTitle.toTypedArray(), mCategoryFg)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }
}