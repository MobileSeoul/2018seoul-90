package com.nicname.iyeongjun.dobike.util

import android.location.Location
import com.nicname.iyeongjun.dobike.ui.activities.main.main.tempLocation
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapPoint

fun getDistance(sLat : Double, sLon :Double, eLat : Double, eLon : Double) : Double {

    val start = Location("a")

    start.latitude = sLat
    start.longitude = sLon

    val end = Location("b")
    end.latitude = eLat
    end.longitude = eLon

    return start.distanceTo(end).toDouble()
}