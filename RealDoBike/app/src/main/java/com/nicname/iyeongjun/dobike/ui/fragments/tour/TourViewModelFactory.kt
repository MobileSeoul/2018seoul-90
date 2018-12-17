package com.nicname.iyeongjun.dobike.ui.fragments.tour

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nicname.iyeongjun.gwangju_contest.api.inter.TourApi

class TourViewModelFactory(val tourApi: TourApi) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TourViewModel(tourApi) as T
    }
}