package com.nicname.iyeongjun.dobike.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class RideDataModel(
        var distance: Double,
        var mydistance: Double,

        var time: String,

        var startLat: Double,
        var startLon: Double,

        var tempLat : Double,
        var tempLon : Double,

        var endName: String,
        var endLat: Double,
        var endLon: Double,

        var speed: Double,
        var date : String
){
    @PrimaryKey(autoGenerate = true) var id: Int =0
}