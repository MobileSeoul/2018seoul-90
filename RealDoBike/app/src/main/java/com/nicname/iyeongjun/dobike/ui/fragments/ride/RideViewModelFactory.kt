package com.nicname.iyeongjun.dobike.ui.fragments.ride

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nicname.iyeongjun.dobike.driver.DataDriver

class RideViewModelFactory(val driver: DataDriver) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RideViewModel(driver) as T
    }
}