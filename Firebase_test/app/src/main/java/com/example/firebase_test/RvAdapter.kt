package com.example.firebase_test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(var mList:ArrayList<Model>,var context:Context):
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var raw_name_tv = itemView.findViewById<TextView>(R.id.raw_name_tv)
        var raw__email_tv = itemView.findViewById<TextView>(R.id.raw_email_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var v = LayoutInflater.from(context).inflate(R.layout.rv_raw,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var mData = mList.get(position)

        holder.raw_name_tv.text = mData.name
        holder.raw__email_tv.text = mData.email
    }
}