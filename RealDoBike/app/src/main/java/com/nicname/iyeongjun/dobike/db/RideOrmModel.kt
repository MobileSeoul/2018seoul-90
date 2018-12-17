package com.nicname.iyeongjun.dobike.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.realm.RealmObject


@Entity
class RideOrmModel(
        var distance: Double,
        var mydistance: Double,

        var time: String,

        var startLat: Double,
        var startLon: Double,

        var endName: String,
        var endLat: Double,
        var endLon: Double,
        var speed: Double
){
    @PrimaryKey(autoGenerate = true) var id: Int =0
}
