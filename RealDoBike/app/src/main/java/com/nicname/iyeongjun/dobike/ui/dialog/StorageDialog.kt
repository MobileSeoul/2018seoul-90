package com.nicname.iyeongjun.dobike.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import com.nicname.iyeongjun.dobike.R

class StorageDialog(var activity: Activity?) : Dialog(activity), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_storage)
    }

    override fun onClick(v: View) {
        dismiss()
    }
}