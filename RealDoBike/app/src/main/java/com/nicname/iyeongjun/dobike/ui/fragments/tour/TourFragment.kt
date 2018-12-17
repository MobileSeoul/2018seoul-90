package com.nicname.iyeongjun.dobike.ui.fragments.tour


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.jakewharton.rxbinding2.widget.itemSelections
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.adapter.recycler.tour.TourAdapter
import com.nicname.iyeongjun.dobike.api.model.section.Item
import com.nicname.iyeongjun.dobike.const.sections
import com.nicname.iyeongjun.dobike.ui.activities.detail.startLocation
import com.nicname.iyeongjun.dobike.ui.activities.main.main.tempLocation
import com.nicname.iyeongjun.dobike.ui.dialog.tourReDriver
import com.nicname.iyeongjun.gwangju_contest.extension.convertTypeToInt
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_tour.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

class TourFragment : DaggerFragment() , AnkoLogger{

    var temp: Item = sections.items.filter { it.section == "강남구" }.first()
    var type = "전체"
    @Inject
    lateinit var viewModelFactory: TourViewModelFactory
    lateinit var viewModel: TourViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this, viewModelFactory)[TourViewModel::class.java]

        return inflater.inflate(R.layout.fragment_tour, container, false)
    }

    override fun onResume() {
        super.onResume()
        tourReDriver.onNext(true)
        startLocation = arrayOf(tempLocation[0], tempLocation[1])
        viewModel.tourApi
                .getTourData(lat = temp?.lat.toDouble(),
                        lon = temp?.long.toDouble())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    tourRecyclerView.apply {
                        adapter = TourAdapter(it,activity!!)
                        layoutManager = LinearLayoutManager(activity!!)
                        adapter.notifyDataSetChanged()
                    }
                },{
                    it.printStackTrace()
                })
//
        tourSectionSpinner.apply {
            adapter = ArrayAdapter<String>(activity!!, R.layout.simple_text, viewModel.tourSectionArray)
            itemSelections()
                    .subscribe({
                        temp = sections.items.filter { item -> item.section == viewModel.tourSectionArray[it] }.first()
                        if(type == "전체"){
                            viewModel
                                    .tourApi
                                    .getTourData(lat = temp!!.lat.toDouble(),
                                            lon = temp!!.long.toDouble())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .doOnSubscribe { tourProgressbar.visibility = View.VISIBLE }
                                    .doOnComplete { tourProgressbar.visibility = View.INVISIBLE }
                                    .subscribe({
                                        info { it.body.items.size }
                                        tourRecyclerView.apply {
                                            adapter = TourAdapter(it, activity!!)
                                            layoutManager = LinearLayoutManager(activity!!)
                                            adapter.notifyDataSetChanged()
                                        }
                                    }, {
                                        it.printStackTrace()
                                    })
                        }else{
                            viewModel
                                    .tourApi
                                    .getTourDataWithContenttype(lat = temp!!.lat.toDouble(),
                                            lon = temp!!.long.toDouble(),
                                            contentTypeId = type.convertTypeToInt())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .doOnSubscribe { tourProgressbar.visibility = View.VISIBLE }
                                    .doOnComplete { tourProgressbar.visibility = View.INVISIBLE }
                                    .subscribe({
                                        info { it.body.items.size }
                                        tourRecyclerView.apply {
                                            adapter = TourAdapter(it, activity!!)
                                            layoutManager = LinearLayoutManager(activity!!)
                                            adapter.notifyDataSetChanged()
                                        }
                                    }, {
                                        it.printStackTrace()
                                    })
                        }
                    }, {
                        it.printStackTrace()
                    })
        }
//
        tourTypeSpinner.apply {
            adapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, viewModel.tourTypeArray)
            itemSelections()
                    .subscribe({
                        type = viewModel.tourTypeArray[it]
                        if(type == "전체"){
                            viewModel
                                    .tourApi
                                    .getTourData(lat = temp!!.lat.toDouble(),
                                            lon = temp!!.long.toDouble())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .doOnSubscribe { tourProgressbar.visibility = View.VISIBLE }
                                    .doOnComplete { tourProgressbar.visibility = View.INVISIBLE }
                                    .subscribe({
                                        tourRecyclerView.apply {
                                            adapter = TourAdapter(it, activity!!)
                                            layoutManager = LinearLayoutManager(activity!!)
                                            adapter.notifyDataSetChanged()
                                        }
                                    }, {
                                        it.printStackTrace()
                                    })
                        }else{
                            viewModel
                                    .tourApi
                                    .getTourDataWithContenttype(lat = temp!!.lat.toDouble(),
                                            lon = temp!!.long.toDouble(),
                                            contentTypeId = type.convertTypeToInt())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .doOnSubscribe { tourProgressbar.visibility = View.VISIBLE }
                                    .doOnComplete { tourProgressbar.visibility = View.INVISIBLE }
                                    .subscribe({
                                        info { it.body.items.size }
                                        tourRecyclerView.apply {
                                            adapter = TourAdapter(it, activity!!)
                                            layoutManager = LinearLayoutManager(activity!!)
                                            adapter.notifyDataSetChanged()
                                        }
                                    }, {
                                        it.printStackTrace()
                                    })
                        }

                    }, {
                        it.printStackTrace()
                    })
        }
    }
}
