package io.parrotsoftware.qatest.domain.repositories

import io.parrotsoftware.qatest.domain.models.Product
import io.parrotsoftware.qatest.domain.models.RepositoryResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(accessToken: String, storeId: String): RepositoryResult<List<Product>>

    suspend fun setProductState(
        accessToken: String,
        productId: String,
        isAvailable: Boolean
    ): RepositoryResult<Nothing>

    suspend fun getProductById(id: String): Flow<Product>
}