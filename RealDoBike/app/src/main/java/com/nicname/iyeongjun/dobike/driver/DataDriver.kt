package com.nicname.iyeongjun.dobike.driver

import com.nicname.iyeongjun.dobike.api.model.forecast.ForecastModel
import com.nicname.iyeongjun.dobike.api.model.location.LocationModel
import com.nicname.iyeongjun.dobike.api.model.storage.StorageModel
import com.nicname.iyeongjun.dobike.api.model.theme.Result
import com.nicname.iyeongjun.dobike.api.model.theme.ThemeModel
import com.nicname.iyeongjun.dobike.api.model.tour.TourModel2
import com.nicname.iyeongjun.dobike.api.model.weather.WeatherModel
import io.reactivex.subjects.BehaviorSubject

val detailDriver = BehaviorSubject.create<Result>()


class DataDriver{
    val weatherDriver = BehaviorSubject.create<WeatherModel>()
    val forecastDriver = BehaviorSubject.create<ForecastModel>()
    val tourDriver = BehaviorSubject.create<TourModel2>()
    val locationDriver = BehaviorSubject.create<LocationModel>()
    val themeDriver = BehaviorSubject.create<ThemeModel>()
    val storageDriver = BehaviorSubject.create<StorageModel>()
}
