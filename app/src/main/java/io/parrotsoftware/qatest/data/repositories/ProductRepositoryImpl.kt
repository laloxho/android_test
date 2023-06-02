package io.parrotsoftware.qatest.data.repositories

import io.parrotsoftware.qa_network.data.requests.ApiUpdateProductRequest
import io.parrotsoftware.qa_network.data.responses.ApiProductAvailability
import io.parrotsoftware.qatest.data.datasource.local.LocalDataSource
import io.parrotsoftware.qatest.data.datasource.remote.RemoteDataSource
import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.data.toProduct
import io.parrotsoftware.qatest.data.toProductEntity
import io.parrotsoftware.qatest.domain.models.Product
import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ProductRepository {

    override suspend fun getProducts(
        accessToken: String,
        storeId: String
    ): RepositoryResult<List<Product>> {
        val response = remoteDataSource.getProducts(accessToken, storeId)

        if (response.isError) {
            val products = localDataSource.getProducts().map { it.toProduct() }
            if (products.isEmpty()) {
                return RepositoryResult(
                    errorCode = response.requiredError.requiredErrorCode,
                    errorMessage = response.requiredError.requiredErrorMessage
                )
            }
            return RepositoryResult(products)
        }

        val products = response.requiredResult.results.map { it.toProduct() }
        localDataSource.insertAll(products.map { it.toProductEntity() })
        return RepositoryResult(products)
    }

    override suspend fun setProductState(
        accessToken: String,
        productId: String,
        isAvailable: Boolean
    ): RepositoryResult<Nothing> {
        val body = ApiUpdateProductRequest(
            if (isAvailable) ApiProductAvailability.AVAILABLE
            else ApiProductAvailability.UNAVAILABLE
        )

        val response = remoteDataSource.updateProduct(
            accessToken,
            productId,
            body
        )

        if (response.isError)
            return RepositoryResult(
                errorCode = response.requiredError.requiredErrorCode,
                errorMessage = response.requiredError.requiredErrorMessage
            )

        return RepositoryResult()
    }
    override suspend fun getProductById(id: String): Flow<Product> = flow {
        emit(localDataSource.getProductById(id).toProduct())
    }.flowOn(Dispatchers.IO)
}
