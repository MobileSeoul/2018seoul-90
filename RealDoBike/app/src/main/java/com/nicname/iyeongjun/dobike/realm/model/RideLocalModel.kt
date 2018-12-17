package com.nicname.iyeongjun.dobike.realm.model

import io.realm.RealmObject

public open class RideLocalModel : RealmObject(){
    var distance : Double? = null
    var mydistance : Double? = null

    var time : String? = null

    var startLat : Double? = null
    var startLon : Double? = null

    var endName : String? = null
    var endLat : Double? = null
    var endLon : Double? = null
    var speed : Double? = null
}