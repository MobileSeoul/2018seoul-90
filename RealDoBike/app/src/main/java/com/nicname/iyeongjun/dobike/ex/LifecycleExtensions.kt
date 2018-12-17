package com.nicname.iyeongjun.gwangju_contest.extension

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver

// 옵저버를 라이프사이클에 등록하는 익스텐션
operator fun Lifecycle.plusAssign(observer: LifecycleObserver)
        = this.addObserver(observer)
