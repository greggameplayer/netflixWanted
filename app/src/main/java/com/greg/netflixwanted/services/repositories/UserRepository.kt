package com.greg.netflixwanted.services.repositories

import androidx.annotation.WorkerThread
import com.greg.netflixwanted.entities.User
import com.greg.netflixwanted.services.dao.UserDao

class UserRepository(private val userDao: UserDao) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

}