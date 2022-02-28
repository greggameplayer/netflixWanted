package com.greg.netflixwanted

import com.greg.netflixwanted.controllers.LocalRoomDatabase
import com.greg.netflixwanted.services.repositories.CountryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application : android.app.Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    lateinit var database: LocalRoomDatabase
    lateinit var repository: CountryRepository


    override fun onCreate() {
        super.onCreate()
        database = LocalRoomDatabase.getDatabase(this, applicationScope)
        repository = CountryRepository(database.countryDao())
    }
}
