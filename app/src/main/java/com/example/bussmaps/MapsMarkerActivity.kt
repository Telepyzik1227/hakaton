package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bussmaps.R
import com.google.android.gms.maps.OnMapReadyCallback

abstract class MapsMarkerActivity:AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
//        if (getString(R.string.MAPS_API_KEY).isEmpty()) {
//            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
//        } else {
//
//            if (getString(R.string.MAPS_API_KEY).isEmpty()) {
////Toast.makeText(this, "")
//            }
//        }
    }
}