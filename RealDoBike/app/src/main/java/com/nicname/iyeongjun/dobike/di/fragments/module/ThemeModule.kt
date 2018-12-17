package com.nicname.iyeongjun.dobike.di.fragments.module

import com.nicname.iyeongjun.dobike.driver.DataDriver
import com.nicname.iyeongjun.dobike.ui.fragments.theme.ThemeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ThemeModule{
    @Provides
    fun provideThemeViewModelFactory(driver: DataDriver) = ThemeViewModelFactory(driver)
}