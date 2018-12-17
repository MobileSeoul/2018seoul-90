package com.nicname.iyeongjun.dobike.di.fragments.module

import com.nicname.iyeongjun.dobike.driver.DataDriver
import com.nicname.iyeongjun.dobike.ui.fragments.storage.StorageViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class StorageModule{
    @Provides
    fun provideStorageViewModelFactory(driver: DataDriver) = StorageViewModelFactory(driver)
}