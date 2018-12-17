package com.nicname.iyeongjun.dobike.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(RideOrmModel::class, RideDataModel::class), version = 1)
abstract class RideDatabase : RoomDatabase(){
    abstract fun getRideDao() : RideDao

    companion object {
        private var INSTANCE : RideDatabase? = null

        fun getInstance(context: Context): RideDatabase? {
            if(INSTANCE == null) {
                synchronized(RideDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                            context,
                            RideDatabase::class.java,
                            "ride4.db")
                            .build()
                }
            }
            return INSTANCE
        }
    }
}