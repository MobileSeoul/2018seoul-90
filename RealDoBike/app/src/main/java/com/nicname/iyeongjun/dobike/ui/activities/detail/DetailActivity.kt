package com.nicname.iyeongjun.dobike.ui.activities.detail

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.nicname.iyeongjun.dobike.GlideApp
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.adapter.recycler.detail.DetailAdapter
import com.nicname.iyeongjun.dobike.driver.detailDriver
import com.nicname.iyeongjun.dobike.service.RideEventModel
import com.nicname.iyeongjun.dobike.service.RideService
import com.nicname.iyeongjun.dobike.service.serviceDriver
import com.nicname.iyeongjun.dobike.ui.activities.main.main.tempLocation
import com.nicname.iyeongjun.gwangju_contest.extension.plusAssign
import com.nicname.iyeongjun.gwangju_contest.extension.runOnIoScheduler
import com.nicname.iyeongjun.gwangju_contest.rx.AutoClearedDisposable
import com.nicname.iyeongjun.nanumcar.util.KakaoNavi
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapPolyLine
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject
import android.content.Context.ACTIVITY_SERVICE
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.util.Log
import com.nicname.iyeongjun.dobike.R.id.*
import org.jetbrains.anko.*

var startLocation = arrayOf(0.0,0.0)

class DetailActivity : DaggerAppCompatActivity(), AnkoLogger {

    @Inject lateinit var viewModelFactory: DetailViewModelFactory
    lateinit var viewModel : DetailViewModel
    val disposable = AutoClearedDisposable(this)
    val viewDisposables = AutoClearedDisposable(lifecycleOwner = this,alwaysClearOnStop = false)

    var tMapPolyLine: TMapPolyLine? = null

    var flag = false

    override fun onResume() {
        super.onResume()
        if(flag){
            RideService.stop(this)
            toast("라이딩 정보가 저장되었습니다")
            info { "여기" }
        }
        flag = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        lifecycle += viewDisposables
        viewModel = ViewModelProviders.of(this, viewModelFactory)[DetailViewModel::class.java]

        imgDe.setColorFilter(Color.argb(100, 0, 0, 0))

        disposable += detailDriver
                .subscribe({result ->
                    viewModel
                            .driver
                            .locationDriver
                            .subscribe({model ->
                                val temp = model.results?.filter { it.number == result.number }
                                txtDeTopTitle.text = result.theme
                                txtDeTitle.text = result.theme
                                txtDeInfo.text = result.introduce

                                GlideApp
                                        .with(this)
                                        .load(result.image)
                                        .into(imgDe)

                                btnDe.setOnClickListener {
                                    serviceDriver.onNext(
                                            RideEventModel(
                                                    result.theme,
                                                    temp.first().courseLat,
                                                    temp.first().courseLon,
                                                    tMapPolyLine?.distance!!
                                            )
                                    )

                                    flag = true
                                    RideService.start()
                                    KakaoNavi.startNavi(temp.first().courseLat,temp.first().courseLon,temp.first().courseName,this@DetailActivity)
                                }
                                recyDetail.apply {
                                    adapter = DetailAdapter(temp,this@DetailActivity)
                                    layoutManager = LinearLayoutManager(this@DetailActivity)
                                }
                                if (tempLocation == null) toast("현 위치를 불러올 수 없습니다").show()
                                else {
                                    runOnIoScheduler {
                                        startLocation = arrayOf(tempLocation[0], tempLocation[1])

                                        val tMapPointStart = TMapPoint(tempLocation[0], tempLocation[1]) // SKT타워(출발지)
                                        val tMapPointEnd = TMapPoint(temp.first().courseLat,
                                                temp.first().courseLon) // N서울타워(목적지)
                                        try {
                                            val centerPolyLine = arrayOf(
                                                    ((tMapPointStart.latitude + temp.first().courseLat) / 2),
                                                    ((tMapPointStart.longitude + temp.first().courseLon) / 2)
                                            )
                                            tMapPolyLine = TMapData().findPathData(tMapPointStart, tMapPointEnd)
                                            tMapPolyLine?.lineColor = Color.BLUE
                                            tMapPolyLine?.lineWidth = 2f
                                            runOnUiThread {
                                                tmapView.apply {

                                                    setCenterPoint(
                                                            centerPolyLine[1],centerPolyLine[0]
                                                    )

                                                    addTMapPolyLine("Line1", tMapPolyLine)
                                                    zoomLevel = 12
                                                }
                                            }
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                    }
                                }
                            },{
                                it.printStackTrace()
                            })
                },{
                    it.printStackTrace()
                })
    }
}
