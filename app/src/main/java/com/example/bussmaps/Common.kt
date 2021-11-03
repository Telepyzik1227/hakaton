package com.example.bussmaps

import retrofit2.create

object Common {
    const val BASE_URL = "https://infinite-beyond-70787.herokuapp.com"

    val retrofitServices: RetrofitServices = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}