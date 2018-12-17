package com.nicname.iyeongjun.dobike.adapter.recycler.theme

import android.app.Activity
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nicname.iyeongjun.dobike.GlideApp
import com.nicname.iyeongjun.dobike.R
import com.nicname.iyeongjun.dobike.api.model.theme.Result
import com.nicname.iyeongjun.dobike.driver.detailDriver
import com.nicname.iyeongjun.dobike.ui.activities.detail.DetailActivity
import com.nicname.iyeongjun.gwangju_contest.extension.getTag
import org.jetbrains.anko.startActivity


class ThemeDetailAdapter(val item : List<Result>,val context : Activity) : RecyclerView.Adapter<ThemeDetailAdapter.ThemeDetailViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_theme_detail2,parent,false)
        return ThemeDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ThemeDetailViewHolder, position: Int) {
        holder.apply {
            item[position].let {
                result = it
                theme.text = it.theme
//                time.text = it.totalTime
                tag.text = it.tag.getTag()
                distance.text = it.totalDistance
                GlideApp
                        .with(context)
                        .load(it.image)
                        .into(img)
            }
        }
    }

    inner class ThemeDetailViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val theme = view.findViewById<TextView>(R.id.txtThDe2Title)
        val tag = view.findViewById<TextView>(R.id.txtThDe2Tag)
        val img = view.findViewById<ImageView>(R.id.imgThDe2)
        val distance = view.findViewById<TextView>(R.id.txtThDe2Distance)
        var result : Result? = null
        init {
            img.setColorFilter(Color.argb(100, 0, 0, 0))

            view.setOnClickListener {
                detailDriver.onNext(result!!)
                context.startActivity<DetailActivity>()
            }
        }
//        val img = view.findViewById<ImageView>(R.id.imgThDe)
//        val layout = view.findViewById<ConstraintLayout>(R.id.tourDetailConstrain)
//        val theme = view.findViewById<TextView>(R.id.txtThDeTheme)
//        val time = view.findViewById<TextView>(R.id.txtThDeTime)
//        val distance = view.findViewById<TextView>(R.id.txtThDeDistance)
//        val tag = view.findViewById<TextView>(R.id.txtThDeTag)
//        init {
//            img.setColorFilter(Color.argb(100, 0, 0, 0))
//            val displayMetrics = DisplayMetrics()
//            context.windowManager.defaultDisplay.getMetrics(displayMetrics)
//
//            var width = displayMetrics.widthPixels
//            layout.layoutParams.width = width
//        }
    }
}