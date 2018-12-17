package com.nicname.iyeongjun.dobike.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.icu.util.GregorianCalendar
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.nicname.iyeongjun.dobike.db.RideDataModel
import com.nicname.iyeongjun.dobike.db.RideDatabase
import com.nicname.iyeongjun.dobike.db.RideOrmModel
import com.nicname.iyeongjun.dobike.realm.model.RideLocalModel
import com.nicname.iyeongjun.dobike.ui.activities.detail.startLocation
import com.nicname.iyeongjun.dobike.ui.activities.main.main.tempLocation
import com.nicname.iyeongjun.dobike.ui.activities.main.splash.realm
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import java.util.*
import java.util.concurrent.TimeUnit


val serviceDriver = BehaviorSubject.create<RideEventModel>()
var time: Long = 0

class RideService : Service(), AnkoLogger {

    var locationManager: LocationManager? = null
    var locationListener: LocationListener? = null


    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        bindGps()
    }

    companion object {
        var arr: List<String>? = null

        var task = object : TimerTask() {
            override fun run() {
                time += 1
            }
        }

        var timer = Timer()


        fun start() {
            timer = Timer()
            task = object : TimerTask() {
                override fun run() {
                    time += 1
                }
            }

            time = 0
            timer.schedule(task, 0, 1000)
        }

        fun stop(context: Context) {
            Log.d("RideService","실행되냐")
            try {
                serviceDriver
                        .observeOn(Schedulers.io())
                        .subscribe({
                            Log.d("RideService", "여기 ")
                            var resultTime = ""

                            val tz = TimeZone.getTimeZone("Asia/Seoul")
                            val gc = GregorianCalendar(tz)

                            var result = "${gc.get(Calendar.YEAR)}/${gc.get(Calendar.MONTH)}/${gc.get(Calendar.DATE)}"

                            RideDatabase
                                    .getInstance(context)?.getRideDao()?.insert2(RideDataModel(
                                            it.distance,
                                            0.0,
                                            time.toString(),
                                            startLocation[0],
                                            startLocation[1],
                                            tempLocation[0],
                                            tempLocation[1],
                                            it.endName,
                                            it.endLat,
                                            it.endLon,
                                            arr?.get(4)?.toDouble() ?: 0.0,
                                            result
                                    ))
                        }, {
                            it.printStackTrace()
                        })
            } catch (e: Exception) {
                context.toast("GPS가 꺼져있거나 네트워크 문제로 라이딩 데이터를 저장할 수 없습니다. GPS와 네트워크를 확인해주세요")
            }
            timer.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        info { "종료" }
        locationListener = null
        locationManager = null

//                        realm.beginTransaction()
//                        var temp = realm.createObject(RideLocalModel::class.java)
//
//                        temp.distance = it.distance
//                        temp.endLat = it.endLat
//                        temp.endLon = it.endLon
//                        temp.endName = it.endName
//
//                        temp.startLat = tempLocation[0]
//                        temp.startLon = tempLocation[1]
//
//                        temp.time = time.toString()
//                        temp.speed = arr!![4].toDouble()
//                        temp.mydistance = arr!![4].toDouble() * time
//
//                        realm.commitTransaction()
//
//                        timer.cancel()
//                        time = 0

    }


    @SuppressLint("MissingPermission")
    fun bindGps() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                location.let {
                    arr = arrayListOf(
                            it?.accuracy.toString(),
                            it?.altitude.toString(),
                            it?.latitude.toString(),
                            it?.longitude.toString(),
                            it?.speed.toString(),
                            it?.time.toString()
                    )
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {}

            override fun onProviderDisabled(provider: String?) {}
        }

        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1.0f, locationListener!!)
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1.0f, locationListener!!)
    }
}
