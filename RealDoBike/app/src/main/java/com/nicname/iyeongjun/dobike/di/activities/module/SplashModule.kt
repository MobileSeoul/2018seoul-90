package com.nicname.iyeongjun.dobike.di.activities.module

import com.nicname.iyeongjun.dobike.api.inter.LocationApi
import com.nicname.iyeongjun.dobike.api.inter.StorageApi
import com.nicname.iyeongjun.dobike.api.inter.ThemeApi
import com.nicname.iyeongjun.dobike.driver.DataDriver
import com.nicname.iyeongjun.dobike.ui.activities.main.splash.SplashViewModelFactory
import com.nicname.iyeongjun.gwangju_contest.api.inter.TourApi
import com.nicname.iyeongjun.gwangju_contest.api.inter.WeatherApi
import dagger.Module
import dagger.Provides

@Module
class SplashModule{
    @Provides
    fun provideSplashViewModelFactory(tourApi: TourApi,
                                      weatherApi: WeatherApi,
                                      storageApi: StorageApi,
                                      locationApi: LocationApi,
                                      themeApi: ThemeApi,
                                      driver: DataDriver) = SplashViewModelFactory(tourApi,weatherApi,storageApi,locationApi,themeApi,driver)
}