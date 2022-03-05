package com.greg.netflixwanted.services.interfaces

import com.greg.netflixwanted.beans.RetrofitConfig
import com.greg.netflixwanted.beans.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.*

interface RetrofitService {

    // Endpoint for searching movies or series by title or by date or by countries or by genres
    @GET("search")
    fun search(
        @Query("newdate") date: Date? = null,
        @Query("query") query: String,
        @Query("countrylist") countries: String? = null,
        @Query("genrelist") genres: String? = null,
        @Header("X-RapidAPI-Key") key: String = RetrofitConfig.API_KEY,
        @Header("X-RapidAPI-Host") host: String = RetrofitConfig.BASE_URL.substringAfterLast('/')
    ): Single<Response<SearchResponse>>
}
