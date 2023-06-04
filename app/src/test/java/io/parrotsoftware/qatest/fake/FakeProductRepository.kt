package io.parrotsoftware.qatest.fake

import io.parrotsoftware.qatest.domain.models.Product
import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import io.parrotsoftware.qatest.fake.FakeData.givenListProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProductRepository : ProductRepository {

    override suspend fun getProducts(
        accessToken: String,
        storeId: String,
    ): RepositoryResult<List<Product>> {
        return RepositoryResult(givenListProduct())
    }

    override suspend fun setProductState(
        accessToken: String,
        productId: String,
        isAvailable: Boolean,
    ): RepositoryResult<Nothing> {
        return RepositoryResult()
    }

    override suspend fun getProductById(id: String): Flow<Product> {
        return flow {
            emit(givenListProduct().first())
        }
    }
}
