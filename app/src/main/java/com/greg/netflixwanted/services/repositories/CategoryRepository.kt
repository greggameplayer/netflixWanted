package com.greg.netflixwanted.services.repositories

import androidx.annotation.WorkerThread
import com.greg.netflixwanted.entities.Category
import com.greg.netflixwanted.entities.Country
import com.greg.netflixwanted.services.dao.CategoryDao

class CategoryRepository(private val categoryDao: CategoryDao)  {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(category:Category) {
        categoryDao.insert(category)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAll() = categoryDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getCountryByName(name: String) = categoryDao.getCategoryByName(name)
}