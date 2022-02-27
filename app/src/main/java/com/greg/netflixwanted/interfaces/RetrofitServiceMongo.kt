package com.greg.netflixwanted.interfaces

import com.google.gson.JsonObject
import com.greg.netflixwanted.beans.RetrofitConfig.Companion.DATA_API_KEY
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitServiceMongo {
    @Headers("Content-Type: application/json")
    @POST("action/findOne")
    fun getApiCalls(@Header("api-key") apiKey: String = DATA_API_KEY, @Body body: JsonObject): Observable<Any>

    @Headers("Content-Type: application/json")
    @POST("action/updateOne")
    fun updateApiCalls(@Header("api-key") apiKey: String = DATA_API_KEY, @Body body: JsonObject): Observable<Any>
}
