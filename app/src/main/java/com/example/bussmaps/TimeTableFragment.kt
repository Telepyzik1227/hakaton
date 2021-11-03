package com.example.bussmaps

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.RecyclerViewAdapterTimeTable
import retrofit2.Call
import retrofit2.Response

class TimeTableFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_time_table, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

//        Common.retrofitServices.getNews().enqueue(object : retrofit2.Callback<Users> {
//
//            override fun onFailure(call: Call<Users>, t: Throwable) {
//                Log.d("AAA", t.message.toString())
//                Log.d("AAA", t.localizedMessage)
//                Log.d("AAA", Common.retrofitServices.getNews().request().toString())
//            }
//
//            override fun onResponse(call: Call<Users>, response: Response<Users>) {
//                if (response.isSuccessful) {
//                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
//                    val listView = view.findViewById<ListView>(R.id.recyclerViewTest)
//                    Toast.makeText(requireContext(), "ABOBA", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(
//                        requireContext(),
//                        response.body()!!.data.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    val adapter: ArrayAdapter<User> = ArrayAdapter<User>(
//                        requireContext(),
//                        R.layout.mylist_item,
//                        response.body()!!.data
//                    )
//                    listView.adapter = adapter
//                }
//            }
//        })

        recyclerView.adapter = RecyclerViewAdapterTimeTable()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        return view
    }
}