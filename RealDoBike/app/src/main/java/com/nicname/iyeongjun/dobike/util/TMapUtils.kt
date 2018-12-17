package com.nicname.iyeongjun.nanumcar.util

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.nicname.iyeongjun.dobike.const.T_MAP_KEY
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapTapi

class TMapUtils{
    companion object {
        var tmapApi : TMapTapi? = null
        fun setTmap(activity : Activity){
            tmapApi = TMapTapi(activity)
            tmapApi!!.setSKTMapAuthentication(T_MAP_KEY)
        }

        fun getPoi() = TMapData()
    }
}