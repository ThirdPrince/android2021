package com.example.android2021.kt03.recy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android2021.R

/**
 * @Title: MyAdapter
 * @Package
 * @Description: recycle adapter
 * @author dhl
 * @date 2021 1104
 * @version V1.0
 */
class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_linear_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(position %2 ==0){
            holder.content.isSingleLine = false
        }else{
            holder.content.isSingleLine = true
        }
        holder.title.text = "${position}移动端架构师体系课"
        holder.content.text = "移动开发“两极分化”，没有差不多的“中间层,唯有尽早成长为架构师，你的职业道路才能走的更远更稳"
    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.item_title)
        val content: TextView = view.findViewById(R.id.item_message)
    }
}