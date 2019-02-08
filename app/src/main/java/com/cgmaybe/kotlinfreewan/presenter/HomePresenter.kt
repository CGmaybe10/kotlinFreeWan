package com.cgmaybe.kotlinfreewan.presenter

import com.cgmaybe.kotlinfreewan.data.bean.ItemDetailBean
import com.cgmaybe.kotlinfreewan.data.bean.HomeDataBean
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
class HomePresenter(private val homeView: HomeContract.HomeView, private val mHomeData: MutableList<HomeDataBean>) : HomeContract.HomePresenter {
    private var mHomePage = 0

    /**
     * 刷新数据
     */
    override fun refreshData() {
        mHomePage = 0
        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        apiService.getHomeBanner()//请求banner的数据
            .flatMap { bannerData ->
                mHomeData.clear()
                mHomeData.add(HomeDataBean(HomeAdapter.HOME_BANNER_TYPE, bannerData.data, null))
//                mHomeData.add(HomeDataBean(HomeAdapter.HOME_AREA_TYPE, null, null))
                apiService.getHomeBlog(mHomePage)//请求博客列表
            }
            .flatMap { blogData ->
                Observable.fromIterable(blogData.data.blogListBean)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ItemDetailBean> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(blogItemBean: ItemDetailBean) {
                    mHomeData.add(HomeDataBean(HomeAdapter.HOME_BLOG_TYPE, null, blogItemBean))
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
            .getHomeBlog(mHomePage)
            .flatMap { blogData ->
                Observable.fromIterable(blogData.data.blogListBean)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ItemDetailBean> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(blogItemBean: ItemDetailBean) {
                    mHomeData.add(HomeDataBean(HomeAdapter.HOME_BLOG_TYPE, null, blogItemBean))
                }

                override fun onComplete() {
                    homeView.completeLoadMore()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }
}