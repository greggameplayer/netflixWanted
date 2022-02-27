package com.greg.netflixwanted.services.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greg.netflixwanted.entities.Country

@Dao
interface CountryDao {

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE name = :name")
    suspend fun getCountryByName(name: String): Country

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(country: Country)

    @Query("DELETE FROM country")
    suspend fun deleteAll()
}
