package com.nicname.iyeongjun.dobike.driver

import com.nicname.iyeongjun.dobike.ar.model.ARPoint
import io.reactivex.subjects.BehaviorSubject

class ArData{
    companion object {
        @JvmField var driver =  BehaviorSubject.create<ARPoint>()
    }
}