package com.greg.netflixwanted.services.dao

import androidx.room.*
import com.greg.netflixwanted.entities.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category)

    @Delete
    fun delete(category: Category)

    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE categoryId=:category_id")
    fun getCategory(category_id: String): Category

    @Query("SELECT * FROM category WHERE name=:name")
    fun getCategoryByName(name: String): Category
}