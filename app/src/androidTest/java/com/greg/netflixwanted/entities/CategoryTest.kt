package com.greg.netflixwanted.entities

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.greg.netflixwanted.controllers.LocalRoomDatabase
import com.greg.netflixwanted.services.dao.CategoryDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CategoryTest {
    private lateinit var categoryDao: CategoryDao
    private lateinit var db: LocalRoomDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, LocalRoomDatabase::class.java).build()
        categoryDao = db.categoryDao()
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
            val category: Category = Category("18", "Test")
            categoryDao.insert(category)
            val byName = categoryDao.getCategoryByName("Test")
            Assert.assertEquals(category.name, byName.name)
            Assert.assertEquals(category.categoryId, byName.categoryId)
        }
    }

    @Test
    @Throws(Exception::class)
    fun createAndGetCategoryById() {
        runBlocking {
            val category: Category = Category("20", "Test3")
            categoryDao.insert(category)
            val byName = categoryDao.getCategory(category.categoryId)
            Assert.assertEquals(category.categoryId, byName.categoryId)
            Assert.assertEquals(category.name, byName.name)
        }
    }

    @Test
    @Throws(Exception::class)
    fun createGetCategoryByNameAndDeleteCategory() {
        runBlocking {
            val category: Category = Category("19", "Test2")
            categoryDao.insert(category)
            categoryDao.delete(category)
            val categoryAfterDel = categoryDao.getCategoryByName("Test2")
            Assert.assertNull(categoryAfterDel)
        }
    }

    @Test
    @Throws(Exception::class)
    fun getAllCategories() {
        runBlocking {
            val categories : Collection<Category>
            val category: Category = Category("21", "Test4")
            categoryDao.insert(category)
            categories = categoryDao.getAll()
            Assert.assertFalse(categories.isEmpty())
        }
    }


}