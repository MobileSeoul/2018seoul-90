package com.nicname.iyeongjun.dobike.ui.fragments.weather


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.gwangju_contest.extension.getResource
import com.nicname.iyeongjun.gwangju_contest.extension.toIntViaDouble
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_weather.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class WeatherFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: WeatherViewModelFactory
    lateinit var viewModel : WeatherViewModel

    var flag = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[WeatherViewModel::class.java]
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }


    override fun onResume() {
        super.onResume()
        if(flag == false){
            activity!!.toast("GPS나 Network에 꺼져있는경우, 정확한 정보제공을 어려울수 있습니다.")
            flag = true
        }
            viewModel
                    .driver
                    .weatherDriver
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                        val item = it.weather?.hourly!![0]
                        txtWeatherRain
                        txtWeatherMinmax.text = "최고기온 : ${item?.temperature?.tmax?.toIntViaDouble()}° / 최저기온 : ${item?.temperature?.tmin?.toIntViaDouble()}°"
                        txtWeatherAver.text = "${item?.temperature?.tc?.toIntViaDouble()}°"

                        val location = item?.grid
                        txtWeatherTitle.text = "${location?.county}, ${location?.village}"
                    }, {
                        it.printStackTrace()
                    })
            viewModel
                    .driver
                    .forecastDriver
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        val temp = it.weather.forecast3days[0].fcst3hour.temperature
                        txtFor4temp.text = "${temp.temp4hour.toIntViaDouble()}°"
                        txtFor7temp.text = "${temp.temp7hour.toIntViaDouble()}°"
                        txtFor10temp.text = "${temp.temp10hour.toIntViaDouble()}°"
                        txtFor13temp.text = "${temp.temp13hour.toIntViaDouble()}°"
                        txtFor16temp.text = "${temp.temp16hour.toIntViaDouble()}°"

                        val prop = it.weather.forecast3days[0].fcst3hour.precipitation

                        txtWeatherRain.text = "${prop.prob4hour.toIntViaDouble()}%"
                        txtFor4prop.text = "${prop.prob4hour.toIntViaDouble()}%"
                        txtFor7prop.text = "${prop.prob7hour.toIntViaDouble()}%"
                        txtFor10prop.text = "${prop.prob10hour.toIntViaDouble()}%"
                        txtFor13prop.text = "${prop.prob13hour.toIntViaDouble()}%"
                        txtFor16prop.text = "${prop.prob16hour.toIntViaDouble()}%"

                        val sky = it.weather.forecast3days[0].fcst3hour.sky
                        imgW3.setImageResource(sky.code4hour.getResource())
                        imgW6.setImageResource(sky.code7hour.getResource())
                        imgW9.setImageResource(sky.code10hour.getResource())
                        imgW12.setImageResource(sky.code13hour.getResource())
                        imgW15.setImageResource(sky.code16hour.getResource())
                    }, {
                        it.printStackTrace()
                    })

    }
}

