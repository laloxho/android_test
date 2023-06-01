package io.parrotsoftware.qatest.data.datasource.local

import io.parrotsoftware.qatest.data.datasource.local.database.entities.ProductEntity

interface ProductLocalDataSource {

    suspend fun insertAll(products: List<ProductEntity>)

    suspend fun getProducts(): List<ProductEntity>
}