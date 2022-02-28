package com.greg.netflixwanted.services.repositories

import androidx.annotation.WorkerThread
import com.greg.netflixwanted.entities.UserCountry
import com.greg.netflixwanted.services.dao.UserCountryDao

class UserCountryRepository(private val userCountryDao: UserCountryDao) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(userCountry: UserCountry) {
        userCountryDao.insert(userCountry)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(userCountry: UserCountry) = userCountryDao.delete(userCountry)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAllByUserId(id: Int) = userCountryDao.getAllByUserId(id)
}