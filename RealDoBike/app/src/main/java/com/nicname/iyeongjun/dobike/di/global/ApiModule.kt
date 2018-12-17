package com.nicname.iyeongjun.dobike.di.global

import com.nicname.iyeongjun.dobike.api.inter.LocationApi
import com.nicname.iyeongjun.dobike.api.inter.StorageApi
import com.nicname.iyeongjun.dobike.api.inter.ThemeApi
import com.nicname.iyeongjun.dobike.const.API_URL
import com.nicname.iyeongjun.dobike.const.SERVER_URL
import com.nicname.iyeongjun.dobike.const.T_BASE_URL
import com.nicname.iyeongjun.gwangju_contest.api.inter.TourApi
import com.nicname.iyeongjun.gwangju_contest.api.inter.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule{
    @Provides
    @Singleton
    fun provideWeatherModel(rxAdapter: CallAdapter.Factory,
                            @Named("gson")gsonConverter: Converter.Factory,
                            client: OkHttpClient)
            = Retrofit.Builder()
            .baseUrl(T_BASE_URL)
            .client(client)
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(gsonConverter)
            .build()
            .create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideTourApi(rxAdapter: CallAdapter.Factory,
                       @Named("xml") xmlConverter : Converter.Factory,
                       client: OkHttpClient)
            = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(xmlConverter)
            .build()
            .create(TourApi::class.java)

    @Provides
    @Singleton
    fun provideThemeModel(rxAdapter: CallAdapter.Factory,
                            @Named("gson")gsonConverter: Converter.Factory,
                            client: OkHttpClient)
            = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(client)
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(gsonConverter)
            .build()
            .create(ThemeApi::class.java)

    @Provides
    @Singleton
    fun provideLocationModel(rxAdapter: CallAdapter.Factory,
                          @Named("gson")gsonConverter: Converter.Factory,
                          client: OkHttpClient)
            = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(client)
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(gsonConverter)
            .build()
            .create(LocationApi::class.java)

    @Provides
    @Singleton
    fun provideStorageModel(rxAdapter: CallAdapter.Factory,
                          @Named("gson")gsonConverter: Converter.Factory,
                          client: OkHttpClient)
            = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(client)
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(gsonConverter)
            .build()
            .create(StorageApi::class.java)

}