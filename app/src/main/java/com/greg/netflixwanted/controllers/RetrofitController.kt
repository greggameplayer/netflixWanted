package com.greg.netflixwanted.controllers

import com.greg.netflixwanted.interfaces.RetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitController {
    private val BASE_URL = "https://unogsng.p.rapidapi.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitService() = retrofit.create(RetrofitService::class.java)
}
