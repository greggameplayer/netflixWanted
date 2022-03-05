package com.greg.netflixwanted

import com.greg.netflixwanted.controllers.LocalRoomDatabase
import com.greg.netflixwanted.entities.UserCountry
import com.greg.netflixwanted.services.repositories.CountryRepository
import com.greg.netflixwanted.services.repositories.UserCategoryRepository
import com.greg.netflixwanted.services.repositories.UserCountryRepository
import com.greg.netflixwanted.services.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application : android.app.Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    lateinit var database: LocalRoomDatabase
    lateinit var repository: CountryRepository
    lateinit var userRepository: UserRepository
    lateinit var userCountryRepository: UserCountryRepository
    lateinit var userCategoryRepository: UserCategoryRepository


    override fun onCreate() {
        super.onCreate()
        database = LocalRoomDatabase.getDatabase(this, applicationScope)
        repository = CountryRepository(database.countryDao())
        userRepository = UserRepository(database.userDao())
        userCountryRepository = UserCountryRepository(database.userCountryDao())
        userCategoryRepository = UserCategoryRepository(database.userCategoryDao())

    }
}
