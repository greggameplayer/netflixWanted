package com.greg.netflixwanted.controllers

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.greg.netflixwanted.beans.RetrofitConfig.Companion.BASE_URL
import com.greg.netflixwanted.beans.RetrofitConfig.Companion.BASE_URL_MONGO
import com.greg.netflixwanted.interfaces.RetrofitService
import com.greg.netflixwanted.interfaces.RetrofitServiceMongo
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.json.JSONException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class RetrofitController {

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val retrofitDataMongo = Retrofit.Builder()
            .baseUrl(BASE_URL_MONGO)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getRetrofitService() = retrofit.create(RetrofitService::class.java)

        fun getRetrofitServiceMongo() = retrofitDataMongo.create(RetrofitServiceMongo::class.java)

        fun getRetrofitFindOneParam(): JsonObject {
            var gsonObject = JsonObject()

            try {
                val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val jsonObject = JsonObject()
                val nestedObject = JsonObject()
                nestedObject.addProperty("date", dateFormat.format(LocalDate.now()))
                jsonObject.addProperty("dataSource", "Cluster0")
                jsonObject.addProperty("database", "netflixWanted")
                jsonObject.addProperty("collection", "apiCalls")
                jsonObject.add("filter", nestedObject)

                val jsonParser = JsonParser()
                gsonObject = jsonParser.parse(jsonObject.toString()) as JsonObject

                println("MY gson.JSON:  AS PARAMETER  $gsonObject")
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return gsonObject

        }

        fun getRetrofitUpdateOneParam(): JsonObject {
            var gsonObject = JsonObject()

            try {
                val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val jsonObject = JsonObject()
                val nestedObject = JsonObject()
                val setNestedObject = JsonObject()
                val incNestedObject = JsonObject()
                incNestedObject.addProperty("calls", -1)
                setNestedObject.add("\$inc", incNestedObject)
                nestedObject.addProperty("date", dateFormat.format(LocalDate.now()))
                jsonObject.addProperty("dataSource", "Cluster0")
                jsonObject.addProperty("database", "netflixWanted")
                jsonObject.addProperty("collection", "apiCalls")
                jsonObject.add("filter", nestedObject)
                jsonObject.add("update", setNestedObject)

                val jsonParser = JsonParser()
                gsonObject = jsonParser.parse(jsonObject.toString()) as JsonObject

                println("MY gson.JSON:  AS PARAMETER  $gsonObject")
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return gsonObject
        }
    }
}
