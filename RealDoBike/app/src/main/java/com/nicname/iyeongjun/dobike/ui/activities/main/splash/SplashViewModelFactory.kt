package com.nicname.iyeongjun.dobike.ui.activities.main.splash

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nicname.iyeongjun.dobike.api.inter.LocationApi
import com.nicname.iyeongjun.dobike.api.inter.StorageApi
import com.nicname.iyeongjun.dobike.api.inter.ThemeApi
import com.nicname.iyeongjun.dobike.driver.DataDriver
import com.nicname.iyeongjun.gwangju_contest.api.inter.TourApi
import com.nicname.iyeongjun.gwangju_contest.api.inter.WeatherApi

class SplashViewModelFactory(
        val tourApi: TourApi,
        val weatherApi: WeatherApi,
        val storageApi: StorageApi,
        val locationApi: LocationApi,
        val themeApi: ThemeApi,
        val driver: DataDriver
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(tourApi,weatherApi,storageApi,locationApi,themeApi,driver) as T
    }
}