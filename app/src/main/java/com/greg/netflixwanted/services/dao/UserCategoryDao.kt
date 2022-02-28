package com.greg.netflixwanted.services.dao

import androidx.room.*
import com.greg.netflixwanted.entities.UserCategory

@Dao
interface UserCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userCategory: UserCategory)

    @Delete
    fun delete(userCategory: UserCategory)

    @Query("SELECT * FROM UserCategory")
    fun getAll(): List<UserCategory>
}