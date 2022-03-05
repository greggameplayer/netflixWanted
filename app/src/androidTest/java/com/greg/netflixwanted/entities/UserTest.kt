package com.greg.netflixwanted.entities

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.greg.netflixwanted.controllers.LocalRoomDatabase
import com.greg.netflixwanted.services.dao.UserDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserTest {
    private lateinit var userDao: UserDao
    private lateinit var db: LocalRoomDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, LocalRoomDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun createAndGetUser() {
        runBlocking {
            val user: User = User(1, "27/02/1995")
            userDao.insert(user)
            val byId = userDao.getUser(1)
            Assert.assertEquals(user.userId, byId.userId)
            Assert.assertEquals(user.userId, byId.userId)
        }
    }

    @Test
    @Throws(Exception::class)
    fun getAllUsers() {
        runBlocking {
            val user: User = User(1, "27/02/1995")
            val users: Collection<User>
            userDao.insert(user)
            users = userDao.getAll()
            Assert.assertFalse(users.isEmpty())
        }
    }

    @Test
    @Throws(Exception::class)
    fun delUser() {
        runBlocking {
            val user: User = User(1, "27/02/1995")
            val users: Collection<User>
            userDao.insert(user)
            userDao.delete(user)
            users = userDao.getAll()
            Assert.assertTrue(users.isEmpty())
        }
    }
}