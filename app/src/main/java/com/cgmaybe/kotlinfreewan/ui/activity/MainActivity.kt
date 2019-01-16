package com.cgmaybe.kotlinfreewan.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.cgmaybe.kotlinfreewan.R
import com.cgmaybe.kotlinfreewan.data.bean.BaseResult
import com.cgmaybe.kotlinfreewan.data.bean.HomeBannerBean
import com.cgmaybe.kotlinfreewan.data.remote.ApiService
import com.cgmaybe.kotlinfreewan.data.remote.RetrofitHelper
import com.cgmaybe.kotlinfreewan.presenter.contractinterface.MainContract
import com.cgmaybe.kotlinfreewan.ui.adapter.MainAdapter
import com.cgmaybe.kotlinfreewan.ui.fragment.HomeFragment
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        initView()
        setListener()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private fun initData() {
        Logger.addLogAdapter(AndroidLogAdapter())

        val observer = object : Observer<BaseResult<List<HomeBannerBean>>> {
            override fun onComplete() {
                Log.d("moubiao", "complete---->")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d("moubiao", "onSubscribe---->")
            }

            override fun onNext(t: BaseResult<List<HomeBannerBean>>) {
                val result = t.data
                for (item in result) {
                    Log.d(
                        "moubiao",
                        "onNext----code = ${t.errorCode} message = ${t.errorMsg} + first data = ${item.title}"
                    )
                }
//                Logger.d(result)
            }

            override fun onError(e: Throwable) {
                Log.d("moubiao", "onError---->")
            }

        }
        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        apiService.getHomeBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

        val myObservable = Observable.create(ObservableOnSubscribe<String> {


        })
    }

    private fun initView() {
        mMainBottomNB
            .addItem(BottomNavigationItem(R.drawable.home, getString(R.string.main_home)))
            .addItem(BottomNavigationItem(R.drawable.system, getString(R.string.main_system)))
            .addItem(BottomNavigationItem(R.drawable.navigation, getString(R.string.main_navigation)))
            .addItem(BottomNavigationItem(R.drawable.project_256, getString(R.string.main_project)))
            .setFirstSelectedPosition(0)
            .setMode(MODE_FIXED)
            .initialise()

        val mainData: MutableList<Fragment> = arrayListOf()
        mainData.add(HomeFragment())
        mMainVP.adapter = MainAdapter(supportFragmentManager, mainData)
    }

    private fun setListener(){
        mMainBottomNB.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {

            }

            override fun onTabSelected(position: Int) {
                Log.d("moubiao", "pos = $position")
            }

        })
    }
}
