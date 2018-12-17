package com.nicname.iyeongjun.dobike.di.fragments.module

import com.nicname.iyeongjun.dobike.driver.DataDriver
import com.nicname.iyeongjun.dobike.ui.fragments.ride.RideViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RideModule{
    @Provides
    fun provideRideViewModelFactory(driver: DataDriver) = RideViewModelFactory(driver)
}