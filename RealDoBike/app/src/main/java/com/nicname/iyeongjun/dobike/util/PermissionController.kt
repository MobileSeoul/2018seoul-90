package com.nicname.iyeongjun.dobike.util

import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
// 권한 요청 클래스
class PermissionController(val activity: AppCompatActivity, val permissions: Array<String>) : AnkoLogger {

    companion object {
        val REQ_FLAG  = 10005

        fun onCheckResult(grantResults: IntArray): Boolean {
            var checkResult = true
            // 권한처리 결과값을 반복문을 돌면서 확인한 후 하나라도 승인되지 않았다면 false를 리턴해준다
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false
                    break
                }
            }
            return checkResult
        }

    }

    fun checkVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission()
            info { 1 }
        } else {
            callInit(activity)
            info { 2 }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private fun checkPermission() {
        var isGranted = true
        info { 3 }
        for (permssion in permissions) {
            info { 4 }
            if (activity.checkSelfPermission(permssion) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false
                break
            }
        }

        if (!isGranted) {
            info { 5 }
            activity.requestPermissions(permissions, REQ_FLAG)
        } else{
            info { 6 }
            callInit(activity)
        }

    }

    private fun callInit(activity: Activity){
        if(activity is CallBack){
            info { 7 }
            (activity as CallBack).init()
        } else {
            info { 8 }
            throw RuntimeException("must implement this.CallBack")
        }
    }

    interface CallBack{
        fun init()

    }
}