package com.greg.netflixwanted.controllers

import com.greg.netflixwanted.beans.RetrofitConfig.Companion.BASE_URL
import com.greg.netflixwanted.interfaces.RetrofitService
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitController {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitService() = retrofit.create(RetrofitService::class.java)
}
