package io.parrotsoftware.qatest

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import io.parrotsoftware.qatest.data.datasource.local.database.AppDatabase
import io.parrotsoftware.qatest.data.datasource.local.database.dao.ProductsDao
import io.parrotsoftware.qatest.data.datasource.local.database.entities.ProductEntity
import io.parrotsoftware.qatest.domain.models.Category
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductDaoTest {

    private lateinit var context: Context
    private lateinit var productsDao: ProductsDao
    private lateinit var database: AppDatabase
    private val category = Category("bbc22898-7bd3-4512-8b09-64c4e19d7a9b", "Combos Especiales", 99)
    private val product1 = ProductEntity(
        "2618ec65-f996-4b12-898b-b6cf1cc32384",
        "Combo Amigos",
        "2 Subs de 15 cm (elige entre Jamón de Pavo, Sub de Pollo",
        "https://d1ralsognjng37.cloudfront.net/b49451f6-4f81-404e-bb97-2e486100b2b8.jpeg",
        189.00f,
        false, category
    )

    private val product2 = ProductEntity(
        "9d1e3446-f536-4842-8adf-8a06e96ab0a9",
        "Combo Amigos - COPIA",
        "2 Subs de 15 cm (elige entre Jamón de Pavo, Sub de Pollo o Atún)",
        "https://d1ralsognjng37.cloudfront.net/b49451f6-4f81-404e-bb97-2e486100b2b8.jpeg",
        189.00f,
        false, category
    )

    private val products = listOf(product1, product2)

    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * Initialize variables necessary to run Dao tests.
     *
     * [database] is initialize as [Room.inMemoryDatabaseBuilder]:
     * according to documentation this method creates a RoomDatabase.Builder for an in memory database.
     * Information stored in an in memory database disappears when the process is killed.
     * Once a database is built, you should keep a reference to it and re-use it.
     */
    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        productsDao = database.productsDao()
    }

    @Test
    fun insertTest() = runBlocking {
        productsDao.insertAll(products)

        val productsDb = productsDao.getProducts().first()
        assertEquals(productsDb.id, "2618ec65-f996-4b12-898b-b6cf1cc32384")
        assertEquals(productsDb.name, "Combo Amigos")
        assertEquals(productsDb.price, 189.00f)
    }

    @Test
    fun getAllTest() = runBlocking {
        productsDao.insertAll(products)

        val productsDb = productsDao.getProducts()
        assertEquals(2, productsDb.size)
    }

    @Test
    fun getProductByIdTest() = runBlocking {
        productsDao.insertAll(products)
        val productsDb = productsDao.getProductById("2618ec65-f996-4b12-898b-b6cf1cc32384")
        assertEquals(productsDb.id, "2618ec65-f996-4b12-898b-b6cf1cc32384")
        assertEquals(productsDb.name, "Combo Amigos")
        assertEquals(productsDb.price, 189.00f)
    }

    @Test
    fun updateProductTest() = runBlocking {
        productsDao.insertAll(products)
        productsDao.updateProduct(1, "2618ec65-f996-4b12-898b-b6cf1cc32384")
        val productsDb = productsDao.getProductById("2618ec65-f996-4b12-898b-b6cf1cc32384")
        assertEquals(productsDb.isAvailable, true)
    }

    @Test
    fun getAllEmptyTest() = runBlocking {
        val productsDb = productsDao.getProducts()
        assertTrue(productsDb.isEmpty())
    }

    @Test
    fun deleteAllTest() = runBlocking {
        productsDao.insertAll(products)

        assertEquals(2, productsDao.getProducts().size)

        productsDao.deleteAll()
        assertTrue(productsDao.getProducts().isEmpty())
    }

    @Test
    fun deleteAllEmptyTest() = runBlocking {
        productsDao.deleteAll()
        val productsDb = productsDao.getProducts()
        assertTrue(productsDb.isEmpty())
    }

    /**
     * Close the DB when tests is finish.
     */
    @After
    fun tearDown() {
        database.close()
    }
}
