package com.nicname.iyeongjun.dobike.ui.fragments.tour

import android.arch.lifecycle.ViewModel
import com.nicname.iyeongjun.dobike.const.sections
import com.nicname.iyeongjun.gwangju_contest.api.inter.TourApi

class TourViewModel(val tourApi: TourApi) : ViewModel(){
    val tourTypeArray = arrayListOf("전체", "관광", "문화시설", "행사/공연", "레포츠", "숙박", "쇼핑", "음식점", "여행코스", "문화")
    val tourSectionArray = sections.items.map { it.section }.sortedBy { it }
}