package com.nicname.iyeongjun.dobike.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface RideDao{

    @Query("select * from RideOrmModel")
    fun getRideData() : List<RideOrmModel>

    @Insert
    fun insert(vararg rideOrmModel: RideOrmModel)


    @Query("select * from RideDataModel")
    fun getRideDataModel() : List<RideDataModel>

    @Insert
    fun insert2(vararg rideDataModel: RideDataModel)
}