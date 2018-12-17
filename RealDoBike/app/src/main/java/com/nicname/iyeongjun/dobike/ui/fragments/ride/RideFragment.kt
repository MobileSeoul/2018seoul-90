package com.nicname.iyeongjun.dobike.ui.fragments.ride

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.R.id.rideRecyclerView
import com.nicname.iyeongjun.dobike.adapter.recycler.ride.RideAdapter
import com.nicname.iyeongjun.dobike.db.RideDatabase
import com.nicname.iyeongjun.dobike.realm.model.RideLocalModel
import com.nicname.iyeongjun.dobike.ui.activities.main.main.rideSnackDriver
import com.nicname.iyeongjun.dobike.ui.activities.main.splash.realm
import com.nicname.iyeongjun.gwangju_contest.extension.runOnIoScheduler
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_ride.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

class RideFragment : DaggerFragment(), AnkoLogger {

    @Inject
    lateinit var viewModelFactory: RideViewModelFactory
    lateinit var viewModel : RideViewModel

    var fragmentView : View? = null

    var flag = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ride, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[RideViewModel::class.java]
        fragmentView = view
        return view
    }

    override fun onResume() {
        super.onResume()
        rideSnackDriver.subscribe {
            if(!flag) {
                Snackbar.make(fragmentView!!, "관광정보나 테마에서 네비 이용시, 자동으로 라이딩 기록이 되어 보여집니다.", Snackbar.LENGTH_LONG).show()
                flag = true
            }
        }

        runOnIoScheduler {
            val temp = RideDatabase.getInstance(activity!!)?.getRideDao()?.getRideDataModel()
            info { temp }
            info { temp?.size }
            for ( i in temp!!){
                info { i.toString() }
                info { i.distance }
                info { i.date }
            }

            activity?.runOnUiThread {
                rideRecyclerView.apply {
                    adapter = RideAdapter(temp,activity!!)
                    layoutManager = LinearLayoutManager(activity!!)
                    adapter.notifyDataSetChanged()
                }
            }
        }

    }
}
