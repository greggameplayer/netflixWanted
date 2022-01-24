package com.greg.netflixwanted.interfaces

import com.greg.netflixwanted.beans.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.*

interface RetrofitService {

    // Endpoint for searching movies or series by title or by date or by countries or by genres
    @GET("/search")
    suspend fun search(@Query("newdate") date: Date?, @Query("query") query: String,
                       @Query("countrylist") countries : String?, @Query("genrelist") genres : String?,
                       @Header("X-RapidAPI-Key") key: String, @Header("X-RapidAPI-Host") host: String = "unogsng.p.rapidapi.com"): Response<SearchResponse>
}
