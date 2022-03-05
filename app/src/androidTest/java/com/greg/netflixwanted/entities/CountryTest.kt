package com.greg.netflixwanted.entities

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.greg.netflixwanted.controllers.LocalRoomDatabase
import com.greg.netflixwanted.services.dao.CountryDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CountryTest {
    private lateinit var countryDao: CountryDao
    private lateinit var db: LocalRoomDatabase


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, LocalRoomDatabase::class.java).build()
        countryDao = db.countryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun createAndGetCategoryByName() {
        runBlocking {
            val country: Country = Country(18, "Test", "code", "img")
            countryDao.insert(country)
            val byName = countryDao.getCountryByName("Test")
            Assert.assertEquals(country.name, byName.name)
            Assert.assertEquals(country.id, byName.id)
            Assert.assertEquals(country.countrycode, byName.countrycode)
            Assert.assertEquals(country.img, byName.img)
        }
    }

    @Test
    @Throws(Exception::class)
    fun getListOfCountries() {
        runBlocking {
            val country: Country = Country(18, "Test", "code", "img")
            countryDao.insert(country)
            val countries: Collection<Country> = countryDao.getAllCountries()
            Assert.assertFalse(countries.isEmpty())
        }
    }

    @Test
    @Throws(Exception::class)
    fun delCountries() {
        runBlocking {
            val country: Country = Country(18, "Test", "code", "img")
            countryDao.insert(country)
            countryDao.deleteAll()
            val countries: Collection<Country> = countryDao.getAllCountries()
            Assert.assertTrue(countries.isEmpty())
        }
    }



}