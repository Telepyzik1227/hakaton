package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bussmaps.MapsActivity
import com.example.bussmaps.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RecyclerViewAdapterTimeTable :
    RecyclerView.Adapter<RecyclerViewAdapterTimeTable.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBing(position: Int) {
            val stops = arrayOf("1. Остановка 1", "2. Остановка 2", "3. Остановка 3")
            val listView = itemView.findViewById<ListView>(R.id.listViewItem)
            listView.adapter = ArrayAdapter(
                itemView.context,
                R.layout.mylist_item,
                stops
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.route_item, parent, false)
        itemView.findViewById<FloatingActionButton>(R.id.fabMap).setOnClickListener {
            itemView.findNavController().navigate(R.id.action_timeTableFragment_to_polyActivity)
//            (TimeTableFragment().activity as MapsActivity?)?.moveToMap()
        }
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBing(position)
    }

    override fun getItemCount(): Int = data.routes.size
}