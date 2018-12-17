package com.nicname.iyeongjun.dobike.ui.activities.main.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nicname.iyeongjun.dobike.driver.DataDriver
import com.nicname.iyeongjun.gwangju_contest.api.inter.WeatherApi

// ViewModel을 만드는 뷰모델 팩토리

class MainViewModelFactory(val driver: DataDriver,
                           val weatherApi: WeatherApi) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(driver, weatherApi) as T
    }
}