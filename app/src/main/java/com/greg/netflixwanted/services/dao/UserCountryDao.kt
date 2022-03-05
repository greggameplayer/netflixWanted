package com.greg.netflixwanted.services.dao

import androidx.room.*
import com.greg.netflixwanted.entities.UserCategory
import com.greg.netflixwanted.entities.UserCountry

@Dao
interface UserCountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userCountry: UserCountry)

    @Delete
    fun delete(userCategory: UserCountry)

    @Query("SELECT * FROM UserCountry WHERE idUser=:id")
    fun getAllByUserId(id : Int?): List<UserCountry>
}