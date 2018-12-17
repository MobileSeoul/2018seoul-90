package com.nicname.iyeongjun.dobike.ui.fragments.storage


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.jakewharton.rxbinding2.widget.RxAdapterView.itemSelections
import com.jakewharton.rxbinding2.widget.itemSelections

import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.R.id.*
import com.nicname.iyeongjun.dobike.api.model.storage.Result
import com.nicname.iyeongjun.dobike.api.model.storage.StorageModel
import com.nicname.iyeongjun.dobike.ar.ARActivity
import com.nicname.iyeongjun.dobike.ar.AROverlayView
import com.nicname.iyeongjun.dobike.ar.model.ARPoint
import com.nicname.iyeongjun.dobike.const.sections
import com.nicname.iyeongjun.dobike.ui.activities.main.main.tempLocation
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_storage.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject


class StorageFragment : DaggerFragment(), OnMapReadyCallback, AnkoLogger,GoogleMap.OnInfoWindowClickListener , GoogleMap.OnMarkerClickListener{
    @Inject
    lateinit var viewModelFactory: StorageViewModelFactory
    lateinit var viewModel : StorageViewModel
    var mapView: MapView? = null
    var googleMap : GoogleMap? = null
    var storageModel : StorageModel? = null
    var tempResult : Result? = null
    var flag = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_storage, container, false)
        mapView = view.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[StorageViewModel::class.java]
        return view
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()

        val storageList = sections.items.map { it.section }.sortedBy { it }

        storageSpinner.apply {
            prompt = "default"
            adapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, storageList)
            itemSelections()
                    .subscribe({
                        val section = sections.items.filter { item -> item.section == storageList[it] }.first()
                        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(section.lat.toDouble(), section.long.toDouble()), 13.5f))
                    }, {
                        it.printStackTrace()
                    })
        }

        btnSt.setOnClickListener {
            if(flag) {
                AROverlayView.tempAR = ARPoint(tempResult?.title,
                        tempResult?.lat!!,
                        tempResult?.lon!!,
                        0.0)
                activity!!.startActivity<ARActivity>()
            }else{
                activity!!.toast("증강현실로 볼 지점을 선택해주십시오")
            }
        }
        viewModel
                .driver
                .storageDriver
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    storageModel = it
                    mapView?.getMapAsync(this)
                }
    }


    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onInfoWindowClick(map: Marker?) {

    }

    override fun onMarkerClick(marker : Marker?): Boolean {
        flag = true
        info { marker?.title }
        val temp = storageModel?.results?.filter { it.title == marker?.title }?.first()

        tempResult = temp

        txtStTitle.text = temp?.title?.trim()
        txtStLocation.text = temp?.location
        txtStQuantity.text = "${temp?.quantity}개"

        return false
    }

    override fun onMapReady(map: GoogleMap?) {

        googleMap = map

        if(tempLocation[0] != null && tempLocation[1] != null){
            info { tempLocation }
            val location = LatLng(tempLocation[0], tempLocation[1])
            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14.5f))
        }else{
            activity!!.toast("GPS를 확인할수 없습니다 기본인 강남구로 세팅됩니다.")
            val temp = sections.items.filter { it.section == "강남구" }.first()// 강남구 주소로 초기세팅
            val location = LatLng(temp.lat.toDouble(), temp.long.toDouble())
            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14.5f))
        }

        map?.setOnMarkerClickListener(this)

        for (i in storageModel?.results!!) {
            try {
                val tempLocation = LatLng(i.lat, i.lon)
                val maker = MarkerOptions()
                        .position(tempLocation)
                        .snippet(i.location)
                        .title(i.title)
                map?.addMarker(maker)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
