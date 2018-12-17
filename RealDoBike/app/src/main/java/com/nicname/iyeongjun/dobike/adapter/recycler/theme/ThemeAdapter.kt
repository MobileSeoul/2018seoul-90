package com.nicname.iyeongjun.dobike.adapter.recycler.theme

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.api.model.theme.ThemeModel


class ThemeAdapter(themeModel: ThemeModel, val activity : Activity) : RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>(){
    val items = themeModel.results?.groupBy { it?.type }
    val subscriptions = arrayOf("서울의 중심, 한강에서 따릉이와 함께",
            "따릉이 타고 서울 밖에 있는 곳으로",
            "서울의 자연을 만나는 숲길코스",
            "연인과 함께 가기 좋은 따릉이 데이트 코스",
            "따릉이와 잠깐의 휴식",
            "고궁, 한옥, 옛길 등 역사 코스 ")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_theme,parent,false)
        return ThemeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.keys?.size!!
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.apply {
            items?.keys?.toList()!![position].let {
                text.text = it
                subscription.text = subscriptions[position]
            }
            items?.values?.toList()!![position].let {
                recyclerView.apply {
                    adapter = ThemeDetailAdapter(it,activity)
//                    layoutManager = GridLayoutManager(context,2)
                    layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    inner class ThemeViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val subscription = view.findViewById<TextView>(R.id.txtThSubscription)
        val text = view.findViewById<TextView>(R.id.txtThemeType)
        val recyclerView = view.findViewById<RecyclerView>(R.id.themeDeatilRecycler)

    }
}