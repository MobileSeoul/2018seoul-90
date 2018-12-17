package com.nicname.iyeongjun.dobike.di.fragments.module

import com.nicname.iyeongjun.dobike.driver.DataDriver
import com.nicname.iyeongjun.dobike.ui.fragments.weather.WeatherViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class WeatherModule{
    @Provides
    fun provideViewModelFactory(driver: DataDriver) = WeatherViewModelFactory(driver)
}