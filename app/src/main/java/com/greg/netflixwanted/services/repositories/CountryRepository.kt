package com.greg.netflixwanted.services.repositories

import androidx.annotation.WorkerThread
import com.greg.netflixwanted.entities.Country
import com.greg.netflixwanted.services.dao.CountryDao

class CountryRepository(private val countryDao: CountryDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(country: Country) {
        countryDao.insert(country)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAll() = countryDao.getAllCountries()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getCountry(name: String) = countryDao.getCountryByName(name)
}
