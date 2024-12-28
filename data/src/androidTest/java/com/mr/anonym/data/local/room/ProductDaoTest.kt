package com.mr.anonym.data.local.room

import androidx.arch.core.executor.TaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mr.anonym.data.getOrAwaitValue
import com.mr.anonym.domain.model.ProductsEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@SmallTest
class ProductDaoTest {

    lateinit var database:RoomInstance
    lateinit var dao:ProductsDAO

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupInstances(){
        database = Room.databaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            RoomInstance::class.java,
            "roomDB"
        ).allowMainThreadQueries().build()
        dao = database.productsDAO
    }
    @After
    fun closeInstances(){
        database.close()
    }

    @Test
    fun insertProductTest() = runBlocking {
        val product = ProductsEntity(id = 1,"kia", isChecked = false)
        dao.insertProduct(product)
        val getAll = dao.getAllProductsLocal().getOrAwaitValue()
        assertThat(getAll).contains(product)
    }
}