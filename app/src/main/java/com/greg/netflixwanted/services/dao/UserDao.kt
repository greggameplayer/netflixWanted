package com.greg.netflixwanted.services.dao

import androidx.room.*
import com.greg.netflixwanted.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE userId=:user_id")
    fun getUser(user_id: Int): User
}