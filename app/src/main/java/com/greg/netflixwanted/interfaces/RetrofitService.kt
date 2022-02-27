package com.greg.netflixwanted.interfaces

import com.greg.netflixwanted.beans.RetrofitConfig
import com.greg.netflixwanted.beans.SearchResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.*

interface RetrofitService {

    // Endpoint for searching movies or series by title or by date or by countries or by genres
    @GET("search")
    fun search(         @Query("newdate") date: Date?,
                        @Query("query") query: String,
                        @Query("countrylist") countries : String?,
                        @Query("genrelist") genres : String?,
                        @Header("X-RapidAPI-Key") key: String = RetrofitConfig.API_KEY,
                        @Header("X-RapidAPI-Host") host: String = RetrofitConfig.BASE_URL.substringAfterLast('/')): Observable<SearchResponse>
}
