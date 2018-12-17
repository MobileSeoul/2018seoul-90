package com.nicname.iyeongjun.dobike.adapter.recycler.ride

import android.app.Activity
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.R.id.info
import com.nicname.iyeongjun.dobike.R.id.tmapView
import com.nicname.iyeongjun.dobike.db.RideDataModel
import com.nicname.iyeongjun.dobike.ui.activities.detail.startLocation
import com.nicname.iyeongjun.dobike.ui.activities.main.main.tempLocation
import com.nicname.iyeongjun.dobike.util.getDistance
import com.nicname.iyeongjun.nanumcar.util.TMapUtils
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapPolyLine
import com.skt.Tmap.TMapView
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class RideAdapter(val list: List<RideDataModel>?, val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AnkoLogger {
    var items: List<RideDataModel>

    init {
        if (list == null) items = listOf<RideDataModel>()
        else items = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ride, parent, false)
        return RideViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as RideViewHolder).apply {
            items[position].let {
                item = it

                var tempdistance = getDistance(it.startLat,it.startLon,it.tempLat,it.tempLon)

//                if(tempdistance < it.distance.toInt() || tempdistance > 1000){
//                    tempdistance = 10.0
//                }

                info { "eLat : ${it.endLat} / eLon : ${it.endLon} / sLat : ${it.startLat} / sLon : ${it.endLon} / tLat : ${it.tempLat} / tLon : ${it.tempLon}"}

                var time = "${it.time.toInt() / 60}분 ${it.time.toInt() % 60}초"
                txtTitle.text = it.endName
                txtDate.text = it.date
                txtTotalDistance.text = "${it.distance.toInt().toString()}m"
                txtTime.text = time
                txtMyDistance.text = "${tempdistance.toInt()}m"

                val progress = ((tempdistance.toInt() * 100) / it.distance.toInt())
                info { "${tempdistance.toInt()} / ${it.distance.toInt()} / $progress" }
                progressBar.progress = progress
            }
        }
    }

    inner class RideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.txtRiReTitle)
        val txtDate = view.findViewById<TextView>(R.id.txtRiReDate)
        val txtMyDistance = view.findViewById<TextView>(R.id.txtRiReMydistance)
        val txtTotalDistance = view.findViewById<TextView>(R.id.txtRiReTotalDistance)
        val txtTime = view.findViewById<TextView>(R.id.txtRiRetime)
        val map = view.findViewById<MapView>(R.id.mapRiRe)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarRiRe)
        var item: RideDataModel? = null

        init {
            if (map != null) {
                map.onCreate(null)
                map.onResume()
                map.getMapAsync {
                    if (item?.endLat != null && item?.endLon != null) {
                        val location = LatLng(item?.endLat!!, item?.endLon!!)
                        it.addMarker(MarkerOptions()
                                .position(location)
                                .title(item?.endName)
                        )
                        it.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13f))
                    }
                }
            }
        }
    }

    inner class FirstRideVieHolder(view: View) : RecyclerView.ViewHolder(view)
}