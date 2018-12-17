package com.nicname.iyeongjun.dobike.di.fragments.module

import com.nicname.iyeongjun.dobike.ui.fragments.tour.TourViewModelFactory
import com.nicname.iyeongjun.gwangju_contest.api.inter.TourApi
import dagger.Module
import dagger.Provides

@Module
class TourModule{
    @Provides
    fun provideTourViewModelFactory(tourApi: TourApi) = TourViewModelFactory(tourApi)
}