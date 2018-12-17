package com.nicname.iyeongjun.dobike.util

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.nicname.iyeongjun.dobike.R

class DefaultSpinnerAdapter(context: Context, res : Int, list : List<String>) : ArrayAdapter<String>(context,res,list){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = super.getView(position, convertView, parent)
        if(position == count){
            v.findViewById<TextView>(R.id.text).apply {
                text = ""
                hint = getItem(count)
            }
        }
        return v
    }

    override fun getCount(): Int {
        return super.getCount() - 1
    }
}