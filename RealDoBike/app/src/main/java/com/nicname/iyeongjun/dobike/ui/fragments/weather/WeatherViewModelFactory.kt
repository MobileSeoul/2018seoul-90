package com.nicname.iyeongjun.dobike.ui.fragments.weather

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nicname.iyeongjun.dobike.driver.DataDriver

class WeatherViewModelFactory(val driver: DataDriver) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(driver) as T
    }
}
