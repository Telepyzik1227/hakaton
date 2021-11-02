package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TimeTableFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_time_table, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = RecyclerViewAdapterTimeTable()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
}