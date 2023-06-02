package io.parrotsoftware.qatest.data.datasource

import io.parrotsoftware.qatest.data.datasource.local.LocalDataSource
import io.parrotsoftware.qatest.data.datasource.local.database.dao.ProductsDao
import io.parrotsoftware.qatest.data.datasource.local.impl.LocalDataSourceImpl
import io.parrotsoftware.qatest.fake.FakeData.givenAccessToken
import io.parrotsoftware.qatest.fake.FakeData.givenCredentials
import io.parrotsoftware.qatest.fake.FakeData.givenListProductEntity
import io.parrotsoftware.qatest.fake.FakeData.givenRefreshToken
import io.parrotsoftware.qatest.fake.FakeData.givenStore
import io.parrotsoftware.qatest.fake.FakePrefsStorage
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class LocalDataSourceTest {

    @Rule @JvmField val mockitoRule: MockitoRule = MockitoJUnit.rule()
    private lateinit var localDataSource: LocalDataSource
    @Mock private lateinit var productsDao: ProductsDao
    @Mock private lateinit var prefsStorage: FakePrefsStorage

    @Before
    fun setUp() {
        localDataSource = LocalDataSourceImpl(productsDao, prefsStorage)
    }

    @Test
    fun `Call insert all from products dao when invoke insert all`() = runTest {
        val products = givenListProductEntity()
        localDataSource.insertAll(products)
        verify(productsDao).insertAll(products)
    }

    @Test
    fun `Call get products from products dao when invoke get products`() = runTest {
        val products = givenListProductEntity()
        given(productsDao.getProducts()).willReturn(products)
        localDataSource.getProducts()
        verify(productsDao).getProducts()
    }

    @Test
    fun `Get products from database when invoke get products`() = runTest {
        val products = givenListProductEntity()
        given(productsDao.getProducts()).willReturn(products)
        val productsDB = localDataSource.getProducts()
        assertThat(productsDB.size, equalTo(2))
        assertThat(productsDB[0].id, equalTo("2618ec65-f996-4b12-898b-b6cf1cc32384"))
        assertThat(productsDB[0].name, equalTo("Combo Amigos"))
        assertThat(productsDB[1].id, equalTo("9d1e3446-f536-4842-8adf-8a06e96ab0a9"))
        assertThat(productsDB[1].name, equalTo("Combo Amigos - COPIA"))
    }

    @Test
    fun `Call get product by id from products dao when invoke get product by id`() = runTest {
        val productId = "2618ec65-f996-4b12-898b-b6cf1cc32384"
        val product = givenListProductEntity().first()
        given(productsDao.getProductById(productId)).willReturn(product)
        localDataSource.getProductById(productId)
        verify(productsDao).getProductById(productId)
    }

    @Test
    fun `Get product by id from database when invoke get product by id`() = runTest {
        val productId = "2618ec65-f996-4b12-898b-b6cf1cc32384"
        val product = givenListProductEntity().first()
        given(productsDao.getProductById(productId)).willReturn(product)
        val productDB = localDataSource.getProductById(productId)
        assertThat(productDB.id, equalTo("2618ec65-f996-4b12-898b-b6cf1cc32384"))
        assertThat(productDB.name, equalTo("Combo Amigos"))
        assertThat(productDB.price, equalTo(189.00f))
        assertThat(productDB.isAvailable, equalTo(false))
    }

    @Test
    fun `Get clear and delete all when invoke clear data method`() = runTest {
        localDataSource.clearData()
        verify(prefsStorage).clear()
        verify(productsDao).deleteAll()
    }

    @Test
    fun `Call set string methods from storage when invoke save credentials`() = runTest {
        val credentials = givenCredentials()
        localDataSource.saveCredentials(credentials.access, credentials.refresh)
        verify(prefsStorage).setString("key_access", credentials.access)
        verify(prefsStorage).setString("key_refresh", credentials.refresh)
    }

    @Test
    fun `Call get string methods from storage when invoke get access`() = runTest {
        given(prefsStorage.getString("key_access")).willReturn(givenAccessToken())
        val access = localDataSource.getAccess()
        assertThat(access, equalTo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6..."))
        verify(prefsStorage).getString("key_access")
    }

    @Test
    fun `Call get string methods from storage when invoke get refresh`() = runTest {
        given(prefsStorage.getString("key_refresh")).willReturn(givenRefreshToken())
        val refresh = localDataSource.getRefresh()
        assertThat(refresh, equalTo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjo..."))
        verify(prefsStorage).getString("key_refresh")
    }

    @Test
    fun `Call set string methods from storage when invoke save store`() = runTest {
        val store = givenStore()
        localDataSource.saveStore(store.id, store.name)
        verify(prefsStorage).setString("key_store_uuid", store.id)
        verify(prefsStorage).setString("key_store_name", store.name)
    }

    @Test
    fun `Call get string method from storage when invoke get store uuid`() = runTest {
        given(prefsStorage.getString("key_store_uuid")).willReturn(givenStore().id)
        val storeId = localDataSource.getStoreUuid()
        assertThat(storeId, equalTo("e7f46731-1654-4ba3-be83-408ac5255838"))
        verify(prefsStorage).getString("key_store_uuid")
    }

    @Test
    fun `Call get string method from storage when invoke get store name`() = runTest {
        given(prefsStorage.getString("key_store_name")).willReturn(givenStore().name)
        val storeName = localDataSource.getStoreName()
        assertThat(storeName, equalTo("Store Android Challenge"))
        verify(prefsStorage).getString("key_store_name")
    }

    @Test
    fun `Call is auth method from data source when invoke is auth`() = runTest {
        given(prefsStorage.getString("key_access")).willReturn(givenAccessToken())
        val isAuth = localDataSource.isAuth()
        assertThat(isAuth, equalTo(true))
        verify(prefsStorage).getString("key_access")
    }
}
