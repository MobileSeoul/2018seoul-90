package com.nicname.iyeongjun.dobike.di

import android.content.Context
import com.nicname.iyeongjun.dobike.DobikeApplication
import com.nicname.iyeongjun.dobike.di.activities.ActivityBinder
import com.nicname.iyeongjun.dobike.di.fragments.FragmentBinder
import com.nicname.iyeongjun.dobike.di.global.ApiModule
import com.nicname.iyeongjun.dobike.di.global.DriverModule
import com.nicname.iyeongjun.dobike.di.global.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


// 의존성 컴포넌트
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ApiModule::class,
        DriverModule::class,
        ActivityBinder::class,
        FragmentBinder::class
        ))
interface AppComponent : AndroidInjector<DobikeApplication> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app : Context) : Builder
        fun build() : AppComponent
    }
}