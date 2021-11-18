package com.example.android2021.kt05

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android2021.R

/**
 * @Title: $
 * @Package $
 * @Description: $(用一句话描述)
 * @author $
 * @date $
 * @version V1.0
 */
class ContactAdapter(val contactList: List<ContactBean>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {


    var onLongItemClickListener: OnLongItemClickListener? = null


    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnLongClickListener {

        init {
            view.setOnLongClickListener(this)
        }

        var name: TextView = view.findViewById(R.id.item_name)
        var num: TextView = view.findViewById(R.id.item_num)
        override fun onLongClick(v: View?): Boolean {
            onLongItemClickListener?.onLongClick(contactList[adapterPosition])
            return true
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

        val contactBean = contactList[position]

        holder.name.text = contactBean.name
        holder.num.text = contactBean.num

    }

    override fun getItemCount(): Int {
        return contactList.size
    }

}