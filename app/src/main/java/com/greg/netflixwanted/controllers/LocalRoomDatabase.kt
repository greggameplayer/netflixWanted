package com.greg.netflixwanted.controllers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import com.greg.netflixwanted.R
import com.greg.netflixwanted.entities.Country
import com.greg.netflixwanted.services.dao.CountryDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.InputStream

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class LocalRoomDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile
        private var INSTANCE: LocalRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LocalRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalRoomDatabase::class.java,
                    "netflix_wanted_db"
                )
                    .build()

                scope.launch {
                    instance.countryDao().deleteAll()

                    val gson = Gson()

                    val inStream : InputStream = context.resources.openRawResource(R.raw.country_data)

                    val jsonString = inStream.bufferedReader().use { it.readText() }

                    gson.fromJson(jsonString, Array<Country>::class.java)?.forEach {
                        instance.countryDao().insert(it)
                    }
                }

                INSTANCE = instance
                instance
            }
        }
    }
}
