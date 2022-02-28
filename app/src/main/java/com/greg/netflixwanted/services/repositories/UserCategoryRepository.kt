package com.greg.netflixwanted.services.repositories

import androidx.annotation.WorkerThread
import com.greg.netflixwanted.entities.UserCategory
import com.greg.netflixwanted.services.dao.UserCategoryDao

class UserCategoryRepository(private val userCategoryDao: UserCategoryDao) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(userCategory: UserCategory) {
        userCategoryDao.insert(userCategory)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getByUserId(userId:Int) {
        userCategoryDao.getAllbyId(userId)
    }
}