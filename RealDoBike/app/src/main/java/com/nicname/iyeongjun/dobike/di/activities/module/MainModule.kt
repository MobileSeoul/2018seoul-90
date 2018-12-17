package com.nicname.iyeongjun.dobike.di.activities.module

import com.nicname.iyeongjun.dobike.driver.DataDriver
import com.nicname.iyeongjun.dobike.ui.activities.main.main.MainViewModelFactory
import com.nicname.iyeongjun.gwangju_contest.api.inter.WeatherApi
import dagger.Module
import dagger.Provides


// 메인 액티비티에 주입되는 모듈
@Module
class MainModule{
    @Provides
    fun provideMainViewModelFactory(driver: DataDriver, weatherApi: WeatherApi) = MainViewModelFactory(driver,weatherApi)
}