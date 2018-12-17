package com.nicname.iyeongjun.dobike.adapter.recycler.detail

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nicname.iyeongjun.dobike.GlideApp
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.api.model.location.Result


class DetailAdapter(val items : List<Result>,
                    val context : Context) : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail,parent,false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.apply {
            items[position].let {
                name.text = it.courseName
                intro.text = it.courseIntroduce
                GlideApp
                        .with(context)
                        .load(it.courseImage)
                        .into(img)
            }
        }
    }

    inner class DetailViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.txtDeReName)
        val intro = view.findViewById<TextView>(R.id.txtDeReIntro)
        val img = view.findViewById<ImageView>(R.id.imgDeRe)

        init {
            img.setColorFilter(Color.argb(100, 0, 0, 0))
        }
    }
}