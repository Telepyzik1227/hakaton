package com.example.bussmaps

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitServices {
    @GET("/users")
    fun getNews(): Call<Users>
//    @Query("language") language:String, @Query("apiKey") apiKey:String
}