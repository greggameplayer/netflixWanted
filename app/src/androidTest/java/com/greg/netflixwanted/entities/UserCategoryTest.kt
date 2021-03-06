package com.greg.netflixwanted.entities

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.greg.netflixwanted.controllers.LocalRoomDatabase
import com.greg.netflixwanted.services.dao.CategoryDao
import com.greg.netflixwanted.services.dao.UserCategoryDao
import com.greg.netflixwanted.services.dao.UserDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserCategoryTest {
    private lateinit var userCategoryDao: UserCategoryDao
    private lateinit var userDao: UserDao
    private lateinit var categoryDao: CategoryDao
    private lateinit var db: LocalRoomDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, LocalRoomDatabase::class.java).build()
        userCategoryDao = db.userCategoryDao()
        userDao = db.userDao()
        categoryDao= db.categoryDao()
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
            val category: Category = Category("18", "Test")
            val user: User = User(1, "27/02/1995")
            val userCategory = UserCategory(1, "18")
            categoryDao.insert(category)
            userDao.insert(user)
            userCategoryDao.insert(userCategory)
            val byUser = userCategoryDao.getAllbyUserId(1)
            Assert.assertEquals(category.categoryId, byUser[0].idCategory)
            Assert.assertEquals(user.userId, byUser[0].idUser)
        }
    }

    @Test
    @Throws(Exception::class)
    fun createAndDelUserCategory() {
        runBlocking {
            val category: Category = Category("18", "Test")
            val user: User = User(1, "27/02/1995")
            val userCategory = UserCategory(1, "18")
            categoryDao.insert(category)
            userDao.insert(user)
            userCategoryDao.insert(userCategory)
            userCategoryDao.delete(userCategory)
            val byUser = userCategoryDao.getAllbyUserId(1)
            Assert.assertTrue(byUser.isEmpty())
        }
    }


}