package com.example.android2021.kt04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.android2021.R
import com.google.android.material.button.MaterialButton

/**
 * @Title:
 * @Package
 * @Description: CourseAdapter
 * @author dhl
 * @date 2021 11 12
 * @version V1.0
 */
class CourseAdapter(val context: Context, private var courseItemList: MutableList<CourseItem>) :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {


    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val item_course_title: TextView = itemView.findViewById(R.id.item_course_title)

        val item_course_label: MaterialButton = itemView.findViewById(R.id.item_course_label)

        val item_course_progress: TextView = itemView.findViewById(R.id.item_course_progress)

        val poster_image: ImageView = itemView.findViewById(R.id.poster_image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_study, parent, false)
        return CourseViewHolder(view)

    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val courseItem = courseItemList[position]
        holder.item_course_label.text = courseItem.label
        holder.item_course_title.text = courseItem.title
        holder.item_course_progress.text = courseItem.progress
        Glide.with(context).load(courseItem.poster).diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(MultiTransformation(RoundedCorners(15))).into(holder.poster_image)
    }

    override fun getItemCount(): Int {
        return courseItemList.size
    }

    fun addCourse(courseItem: CourseItem) {
        if(courseItemList.size == 0){
            courseItemList.add(0, courseItem)
            notifyItemInserted(0)
        }else{
            courseItemList.add(1, courseItem)
            notifyItemInserted(1)
        }


    }

    fun deleteCourse(position: Int) {
        if(courseItemList.size >0) {
            courseItemList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun updateCourse(position: Int,progress:String){
        val courseItem = courseItemList[position]
        courseItem.progress = progress
        notifyItemChanged(position)
    }
}