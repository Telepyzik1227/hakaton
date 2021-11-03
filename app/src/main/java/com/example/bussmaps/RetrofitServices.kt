package com.example.bussmaps

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitServices {
    @GET("/users")
    fun getNews(): Call<Users>
    @GET("/schedule")
    fun getSchedule(): Call<Routes>
//    @Query("language") language:String, @Query("apiKey") apiKey:String
}