package com.nicname.iyeongjun.dobike.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import com.nicname.iyeongjun.dobike.GlideApp
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.api.model.tour.TourModel2
import com.nicname.iyeongjun.dobike.ar.ARActivity
import com.nicname.iyeongjun.dobike.ar.AROverlayView
import com.nicname.iyeongjun.dobike.ar.model.ARPoint
import com.nicname.iyeongjun.dobike.service.RideEventModel
import com.nicname.iyeongjun.dobike.service.RideService
import com.nicname.iyeongjun.dobike.service.serviceDriver
import com.nicname.iyeongjun.dobike.ui.activities.main.main.tempLocation
import com.nicname.iyeongjun.dobike.util.getDistance
import com.nicname.iyeongjun.gwangju_contest.extension.convertType
import com.nicname.iyeongjun.gwangju_contest.extension.getTourToDate
import com.nicname.iyeongjun.nanumcar.util.KakaoNavi
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapPoint
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.dialog_tour.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


val tourReDriver = BehaviorSubject.create<Boolean>()
class TourDialog(var activity: Activity?, val item: TourModel2.BodyBean.ItemBean) : Dialog(activity), View.OnClickListener {
    var flag = false
    var once = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_tour)
        txtDiaTourCreated.text = "${item.createdtime.getTourToDate()}"
        txtDiaTourUpdate.text = "${item.modifiedtime.getTourToDate()}"
        txtDiaTourLocation.text = "${item.addr1}"
        txtDiaTourType.text = "${item.contenttypeid.convertType()}"
        txtDiaTourTitle.text = item.title
        txtDiaTourTel.text = item.tel ?: "전화번호없음"

        imgDiaTour.setColorFilter(Color.argb(100, 0, 0, 0))

        if (item.firstimage != null && item.firstimage != "") {
            GlideApp
                    .with(context)
                    .load(item.firstimage)
                    .into(imgDiaTour)
        } else {
            GlideApp
                    .with(context)
                    .load(R.drawable.defalut_image_tour)
                    .into(imgDiaTour)
        }

        btnDiaTourNavi.setOnClickListener {
            RideService.start()
            flag = true
            KakaoNavi.startNavi(
                    item.mapy.toDouble(),
                    item.mapx.toDouble(),
                    item.title,
                    activity!!
            )
        }

        tourReDriver.subscribe{
            if(it && flag && once){
                val tMapPointStart = TMapPoint(tempLocation[0], tempLocation[1]) // SKT타워(출발지)
                val tMapPointEnd = TMapPoint(item.mapy.toDouble(),
                        item.mapx.toDouble())
                val tMapPolyLine = TMapData().findPathData(tMapPointStart, tMapPointEnd)


                val distance = getDistance(tempLocation[0], tempLocation[1], item.mapy.toDouble(), item.mapx.toDouble())
                serviceDriver.onNext(
                        RideEventModel(
                                item.title,
                                item.mapy.toDouble(),
                                item.mapx.toDouble(),
                                distance
                        )
                )
                Log.d("RideService","TourRedriver  저장됨")
                RideService.stop(activity!!)
                once = false
                activity!!.toast("라이딩 데이터가 저장되었습니다")
            }
        }

        btnDiaTour.setOnClickListener {
            AROverlayView.tempAR = ARPoint(item.title,item.mapy.toDouble(),item.mapx.toDouble(),0.0)
            context.startActivity<ARActivity>()
        }
    }

    override fun onClick(v: View) {
        dismiss()
    }
}