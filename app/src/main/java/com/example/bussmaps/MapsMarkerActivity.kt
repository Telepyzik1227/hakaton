package com.example.bussmaps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.OnMapReadyCallback

abstract class MapsMarkerActivity:AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate ( savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        if(getString(R.string.MAPS_API_KEY).isEmpty()){
//Toast.makeText(this, "")
        }
    }
}