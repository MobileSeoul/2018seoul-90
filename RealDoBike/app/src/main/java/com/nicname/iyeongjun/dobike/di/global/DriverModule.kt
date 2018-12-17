package com.nicname.iyeongjun.dobike.di.global

import com.nicname.iyeongjun.dobike.driver.DataDriver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DriverModule{

    @Provides
    @Singleton
    fun provideDriver() = DataDriver()

}