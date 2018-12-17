package com.nicname.iyeongjun.dobike.di.activities.module

import com.nicname.iyeongjun.dobike.driver.DataDriver
import com.nicname.iyeongjun.dobike.ui.activities.detail.DetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailModule{
    @Provides
    fun provideDetailViewModelFactory(driver: DataDriver) = DetailViewModelFactory(driver)
}