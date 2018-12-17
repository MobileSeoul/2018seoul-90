package com.nicname.iyeongjun.dobike.ui.activities.main.main

import android.arch.lifecycle.ViewModel
import com.nicname.iyeongjun.dobike.driver.DataDriver
import com.nicname.iyeongjun.dobike.ui.fragments.ride.RideFragment
import com.nicname.iyeongjun.dobike.ui.fragments.storage.StorageFragment
import com.nicname.iyeongjun.dobike.ui.fragments.theme.ThemeFragment
import com.nicname.iyeongjun.dobike.ui.fragments.tour.TourFragment
import com.nicname.iyeongjun.dobike.ui.fragments.weather.WeatherFragment
import com.nicname.iyeongjun.gwangju_contest.api.inter.WeatherApi

// 메인 뷰모델
class MainViewModel(val driver: DataDriver,
                    val weatherApi: WeatherApi) : ViewModel(){
    val tabNames = arrayOf("추천","대여소","날씨","관광","라이딩")
    val fragements = listOf(
            ThemeFragment(),
            StorageFragment(),
            WeatherFragment(),
            TourFragment(),
            RideFragment())

}