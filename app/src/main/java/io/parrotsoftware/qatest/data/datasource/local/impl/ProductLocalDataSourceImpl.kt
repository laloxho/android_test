package io.parrotsoftware.qatest.data.datasource.local.impl

import io.parrotsoftware.qatest.data.datasource.local.ProductLocalDataSource
import io.parrotsoftware.qatest.data.datasource.local.database.dao.ProductsDao
import io.parrotsoftware.qatest.data.datasource.local.database.entities.ProductEntity
import javax.inject.Inject

class ProductLocalDataSourceImpl @Inject constructor(private val productsDao: ProductsDao): ProductLocalDataSource {

    override suspend fun insertAll(products: List<ProductEntity>) = productsDao.insertAll(products)

    override suspend fun getProducts(): List<ProductEntity> = productsDao.getProducts()
}
