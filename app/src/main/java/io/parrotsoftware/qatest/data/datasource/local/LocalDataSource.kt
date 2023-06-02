package io.parrotsoftware.qatest.data.datasource.local

import io.parrotsoftware.qatest.data.datasource.local.database.entities.ProductEntity

interface LocalDataSource {

    suspend fun insertAll(products: List<ProductEntity>)

    suspend fun getProducts(): List<ProductEntity>

    suspend fun getProductById(id: String): ProductEntity

    suspend fun clearData()

    fun saveCredentials(access: String, refresh: String)

    fun getAccess(): String

    fun getRefresh(): String

    fun saveStore(uuid: String, name: String)

    fun getStoreUuid(): String

    fun getStoreName(): String

    fun isAuth(): Boolean
}