package com.nicname.iyeongjun.dobike.ui.activities.main.splash

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.api.model.forecast.ForecastModel
import com.nicname.iyeongjun.dobike.api.model.location.LocationModel
import com.nicname.iyeongjun.dobike.api.model.storage.StorageModel
import com.nicname.iyeongjun.dobike.api.model.theme.ThemeModel
import com.nicname.iyeongjun.dobike.api.model.weather.WeatherModel
import com.nicname.iyeongjun.dobike.const.sections
import com.nicname.iyeongjun.dobike.db.RideDataModel
import com.nicname.iyeongjun.dobike.db.RideDatabase
import com.nicname.iyeongjun.dobike.service.RideService
import com.nicname.iyeongjun.dobike.service.time
import com.nicname.iyeongjun.dobike.ui.activities.detail.startLocation
import com.nicname.iyeongjun.dobike.ui.activities.main.main.MainActivity
import com.nicname.iyeongjun.dobike.ui.activities.main.main.tempLocation
import com.nicname.iyeongjun.dobike.util.PermissionController
import com.nicname.iyeongjun.gwangju_contest.extension.plusAssign
import com.nicname.iyeongjun.gwangju_contest.extension.runOnIoScheduler
import com.nicname.iyeongjun.gwangju_contest.rx.AutoClearedDisposable
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxkotlin.Observables
import io.realm.Realm
import javax.inject.Inject
import kotlin.properties.Delegates
import io.realm.RealmConfiguration
import org.jetbrains.anko.*
import java.util.*


var realm : Realm by Delegates.notNull()

class SplashActivity : DaggerAppCompatActivity() , AnkoLogger, PermissionController.CallBack {

    val hd = Handler()

    @Inject lateinit var viewModelFactory: SplashViewModelFactory
    lateinit var viewModel: SplashViewModel
    val disposable = AutoClearedDisposable(this)
    val viewDisposables = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)

    override fun init() {
        startService<RideService>()
        val defaultSection = sections.items.filter { it.section == "강남구" }.first()
        viewModel.apply {
            weatherApi.getWeather(defaultSection.lat, defaultSection.long)
            .subscribe({
                info { "weather : ${it}" }
            },{
                it.printStackTrace()
            })
            Observables
                    .combineLatest(
                            viewModel.locationApi.getLocation(),
                            viewModel.storageApi.getStorage(),
                            viewModel.themeApi.getTheme()
                            ,viewModel.weatherApi.getWeather(defaultSection.lat, defaultSection.long),
                            viewModel.weatherApi.getForecast(defaultSection.lat,defaultSection.long)
                    ) { a1, a2, a3
                        ,a4,a5
                        ->
                        arrayOf(a1,a2,a3
                                ,a4,a5
                        )
                    }.subscribe({
                        driver.apply {
                            locationDriver.onNext(it[0] as LocationModel)
                            storageDriver.onNext(it[1] as StorageModel)
                            themeDriver.onNext(it[2] as ThemeModel)
                            weatherDriver.onNext(it[3] as WeatherModel)
                            forecastDriver.onNext(it[4] as ForecastModel)
                        }
                        hd.postDelayed({
                            startActivity<MainActivity>()
                            finish()
                        },3000)
                        setDb()

                    }, {
                        toast("GPS나 Network에 문제가 있습니다. 정확한 정보제공을 위해, GPS와 네트워크를 켜주세요")
                        it.printStackTrace()
                    })
        }
        setRealm()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycle += viewDisposables
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SplashViewModel::class.java]
        PermissionController(this, arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION
        )).checkVersion()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PermissionController.REQ_FLAG) {
            if (PermissionController.onCheckResult(grantResults)) {
                info { "사용자 확정" }
                init()
            } else {
                Toast.makeText(this, "권한을 허용하지 않으시면 프로그램을 실행할 수 없습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setDb(){
        val temp = RideDatabase.getInstance(this)?.getRideDao()?.getRideDataModel()
        if(temp?.size == 0) {
            runOnIoScheduler {
                val tz = TimeZone.getTimeZone("Asia/Seoul")
                val gc = GregorianCalendar(tz)

                var result = "${gc.get(Calendar.YEAR)}/${gc.get(Calendar.MONTH)}/${gc.get(Calendar.DATE)}"
                RideDatabase
                        .getInstance(this)?.getRideDao()?.insert2(RideDataModel(
                                2530.22,
                                0.0,
                                "1432",
                                37.494947,
                                127.037163,
                                37.511097,
                                127.029928,
                                "한남대교",
                                37.524444, 127.015716,
                                5.0,
                                result
                        ))
            }
        }
    }

    fun setRealm(){
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .build()
        Realm.deleteRealm(realmConfig)

//        Realm.init(this)
//        val config = Realm.getDefaultConfiguration()
//                .name(Realm.DEFAULT_REALM_NAME)
//                .deleteRealmIfMigrationNeeded()
//                .build()

//        realm = Realm.getInstance(config)
//        realm = Realm.getInstance(config)
//        realm = Realm.getInstance(config)
    }
}
