package com.cgmaybe.kotlinfreewan.presenter

import com.cgmaybe.kotlinfreewan.data.bean.HomeBlogDetail
import com.cgmaybe.kotlinfreewan.data.bean.HomeData
import com.cgmaybe.kotlinfreewan.data.remote.ApiService
import com.cgmaybe.kotlinfreewan.data.remote.RetrofitHelper
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.HomeContract
import com.cgmaybe.kotlinfreewan.ui.adapter.HomeAdapter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 首页的presenter
 */
class HomePresenter(private val homeView: HomeContract.HomeView) : HomeContract.HomePresenter {
    private var mHomePage = 0
    private val mHomeData: MutableList<HomeData> = arrayListOf()

    /**
     * 刷新数据
     */
    override fun refreshData() {
        mHomePage = 0
        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        apiService.getHomeBanner()//请求banner的数据
            .flatMap { bannerData ->
                mHomeData.clear()
                mHomeData.add(HomeData(HomeAdapter.HOME_BANNER_TYPE, bannerData.data, null))
                mHomeData.add(HomeData(HomeAdapter.HOME_AREA_TYPE, null, null))
                apiService.getHomeBlog(mHomePage.toString())//请求博客列表
            }
            .flatMap { blogData ->
                Observable.fromIterable(blogData.data.blogList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<HomeBlogDetail> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(blogItem: HomeBlogDetail) {
                    mHomeData.add(HomeData(HomeAdapter.HOME_BLOG_TYPE, null, blogItem))
                }

                override fun onComplete() {
                    homeView.completeRefresh()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData() {
        mHomePage++
        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        apiService
            .getHomeBlog(mHomePage.toString())
            .flatMap { blogData ->
                Observable.fromIterable(blogData.data.blogList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<HomeBlogDetail> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(blogItem: HomeBlogDetail) {
                    mHomeData.add(HomeData(HomeAdapter.HOME_BLOG_TYPE, null, blogItem))
                }

                override fun onComplete() {
                    homeView.completeLoadMore()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }

    /**
     * 获取首页数据
     */
    fun getHomeData(): MutableList<HomeData> {
        return mHomeData
    }
}