package com.nicname.iyeongjun.gwangju_contest.api.inter

import com.nicname.iyeongjun.dobike.api.model.forecast.ForecastModel
import com.nicname.iyeongjun.dobike.api.model.weather.WeatherModel
import com.nicname.iyeongjun.dobike.const.T_KEY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    @Headers("Accept: application/json",
            "Content-type:application/json",
            "appKey:${T_KEY}")
    @GET("weather/current/hourly")
    fun getWeather(
                   @Query("lat") lat : String,
                   @Query("lon") lon : String,
                   @Query("version") version : Int = 2) : Observable<WeatherModel>

    @Headers("Accept: application/json",
            "Content-type:application/json",
            "appKey:${T_KEY}")
    @GET("https://api2.sktelecom.com/weather/forecast/3days")
    fun getForecast(
            @Query("lat") lat : String,
            @Query("lon") lon : String,
            @Query("version") version : Int = 2
    ) : Observable<ForecastModel>
}
