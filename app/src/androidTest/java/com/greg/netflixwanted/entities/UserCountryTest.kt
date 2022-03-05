package com.greg.netflixwanted.entities

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.greg.netflixwanted.controllers.LocalRoomDatabase
import com.greg.netflixwanted.services.dao.CountryDao
import com.greg.netflixwanted.services.dao.UserCountryDao
import com.greg.netflixwanted.services.dao.UserDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserCountryTest {
    private lateinit var userCountryDao: UserCountryDao
    private lateinit var userDao: UserDao
    private lateinit var countryDao: CountryDao
    private lateinit var db: LocalRoomDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, LocalRoomDatabase::class.java).build()
        userCountryDao = db.userCountryDao()
        userDao = db.userDao()
        countryDao= db.countryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun createAndGetUserCategory() {
        runBlocking {
            val country: Country = Country(1, "Test","code", "img")
            val user: User = User(1, "27/02/1995")
            val userCountry = UserCountry(1, 1)
            countryDao.insert(country)
            userDao.insert(user)
            userCountryDao.insert(userCountry)
            val countriesByUser = userCountryDao.getAllByUserId(1)
            Assert.assertEquals(country.id, countriesByUser[0].idCountry)
            Assert.assertEquals(user.userId, countriesByUser[0].idUser)
        }
    }

    @Test
    @Throws(Exception::class)
    fun createAndDelUserCategory() {
        runBlocking {
            val country: Country = Country(1, "Test","code", "img")
            val user: User = User(1, "27/02/1995")
            val userCountry = UserCountry(1, 1)
            countryDao.insert(country)
            userDao.insert(user)
            userCountryDao.insert(userCountry)
            userCountryDao.delete(userCountry)
            val countriesByUser = userCountryDao.getAllByUserId(1)
            Assert.assertTrue(countriesByUser.isEmpty())
        }
    }

}