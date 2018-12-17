
package com.nicname.iyeongjun.dobike.ui.fragments.theme

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.adapter.recycler.theme.ThemeAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_theme.*
import javax.inject.Inject

class ThemeFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ThemeViewModelFactory
    lateinit var viewModel : ThemeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ThemeViewModel::class.java]

        return inflater.inflate(R.layout.fragment_theme, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.driver
                .themeDriver
                .subscribe({
                    themeRecycler.apply {
                        adapter = ThemeAdapter(it,activity!!)
                        layoutManager = LinearLayoutManager(activity!!)
                    }

                },{
                    it.printStackTrace()
                })
    }

}
