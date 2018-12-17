package com.nicname.iyeongjun.dobike.ui.activities.main.main

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.PorterDuff
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.R.id.mainViewpager
import com.nicname.iyeongjun.dobike.R.id.tab
import com.nicname.iyeongjun.dobike.adapter.pager.MainPagerAdapter
import com.nicname.iyeongjun.dobike.const.sections
import com.nicname.iyeongjun.gwangju_contest.rx.AutoClearedDisposable
import com.nicname.iyeongjun.nanumcar.util.TMapUtils
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import javax.inject.Inject


var tempLocation = arrayOf(
        sections.items.filter { it.section == "강남구" }.first().lat.toDouble(),
        sections.items.filter { it.section == "강남구" }.first().long.toDouble()
)

val rideSnackDriver = BehaviorSubject.create<Boolean>()
class MainActivity : DaggerAppCompatActivity(), AnkoLogger {

    val tabIcons = arrayOf(
            R.drawable.icon_theme,
            R.drawable.icon_storage,
            R.drawable.icon_weather,
            R.drawable.icon_tour,
            R.drawable.icon_ride
    )
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory //메인 뷰모델 팩토리
    lateinit var viewModel: MainViewModel
    val disposable = AutoClearedDisposable(this) // 자동삭제 -> 라이플사이클 오너
    val viewDisposables = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false) // 라이플사이클과 싱크 맞춤

    var locationManager: LocationManager? = null
    var locationListener: LocationListener? = null

    var flag = false

    override fun onBackPressed() {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        bindViewPager(viewModel.fragements)
        bindGps(this)
        TMapUtils.setTmap(this)
    }

    private fun bindViewPager(list: List<Fragment>) {
        mainViewpager.adapter = MainPagerAdapter(supportFragmentManager, list)
        for (i in 0..4) tab.addTab(tab.newTab().setText(viewModel.tabNames[i]).setIcon(tabIcons[i]))
        mainViewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))
        tab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mainViewpager))
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tabs: TabLayout.Tab) {
                if(tabs.position == 4){
                    rideSnackDriver.onNext(true)
                }
                val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.icon_selected)
                tabs.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tabs: TabLayout.Tab) {
                val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.icon_unselected)
                tabs.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tabs: TabLayout.Tab) {

            }
        })
    }
    @SuppressLint("MissingPermission")
    fun bindGps(context: Context) {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                location.let {
                    try {
                        info { it?.latitude }
                        info { it?.longitude }
                        tempLocation = arrayOf(it?.latitude!!, it?.longitude!!)
                        viewModel
                                .weatherApi
                                .getWeather(it?.latitude!!.toString(),it?.longitude!!.toString())
                                .subscribe({
                                    viewModel.driver.weatherDriver.onNext(it)
                                },{
                                    toast("GPS나 Network에 문제가 있습니다. 정확한 정보제공을 위해, GPS와 네트워크를 켜주세요")
                                    it.printStackTrace()
                                })
                    }catch (e : Exception){
                        toast("위치 정보를 받아올 수 없습니다. GPS를 확인해주세요")
                        e.printStackTrace()
                    }

                }
            }
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                info { "status : $status" }
            }

            override fun onProviderEnabled(provider: String?) {}

            override fun onProviderDisabled(provider: String?) {}
        }

        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1.0f, locationListener!!)
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1.0f, locationListener!!)
    }
}
