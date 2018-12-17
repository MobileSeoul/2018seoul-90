package com.nicname.iyeongjun.nanumcar.util

import android.app.Activity
import com.kakao.kakaonavi.KakaoNaviParams
import com.kakao.kakaonavi.KakaoNaviService
import com.kakao.kakaonavi.Location
import com.kakao.kakaonavi.NaviOptions
import com.kakao.kakaonavi.options.CoordType
import com.kakao.kakaonavi.options.RpOption
import com.kakao.kakaonavi.options.VehicleType

class KakaoNavi {
    companion object {
        fun startNavi(lat: Double,
                      lon: Double,
                      name: String,
                      activity: Activity) {
            val destination = Location.newBuilder(name, lon, lat).build()
            val options = NaviOptions
                    .newBuilder()
                    .setCoordType(CoordType.WGS84)
                    .setVehicleType(VehicleType.FIRST)
                    .setRpOption(RpOption.FAST)
                    .build()
            val builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options)
            KakaoNaviService.getInstance().shareDestination(activity, builder.build())
        }
    }
}