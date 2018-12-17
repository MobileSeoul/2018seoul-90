package com.nicname.iyeongjun.dobike.di.fragments

import com.nicname.iyeongjun.dobike.di.fragments.module.*
import com.nicname.iyeongjun.dobike.ui.fragments.ride.RideFragment
import com.nicname.iyeongjun.dobike.ui.fragments.storage.StorageFragment
import com.nicname.iyeongjun.dobike.ui.fragments.theme.ThemeFragment
import com.nicname.iyeongjun.dobike.ui.fragments.tour.TourFragment
import com.nicname.iyeongjun.dobike.ui.fragments.weather.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


// 프래그먼트를 대거에 올림
@Module
abstract class FragmentBinder{

    @ContributesAndroidInjector(modules = arrayOf(RideModule::class))
    abstract fun bindRideModule() : RideFragment

    @ContributesAndroidInjector(modules = arrayOf(StorageModule::class))
    abstract fun bindStorageModule() : StorageFragment

    @ContributesAndroidInjector(modules = arrayOf(ThemeModule::class))
    abstract fun bindThemeModule() : ThemeFragment

    @ContributesAndroidInjector(modules = arrayOf(TourModule::class))
    abstract fun bindTourModule() : TourFragment

    @ContributesAndroidInjector(modules = arrayOf(WeatherModule::class))
    abstract fun bindWeatherModule() : WeatherFragment
}
