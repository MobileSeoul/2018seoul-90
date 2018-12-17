package com.nicname.iyeongjun.dobike.ui.fragments.storage

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nicname.iyeongjun.dobike.driver.DataDriver


class StorageViewModelFactory(val driver: DataDriver) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StorageViewModel(driver) as T
    }
}