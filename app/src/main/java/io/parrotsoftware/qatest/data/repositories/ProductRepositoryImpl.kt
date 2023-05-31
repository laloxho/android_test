package io.parrotsoftware.qatest.data.repositories

import io.parrotsoftware.qa_network.domain.requests.ApiUpdateProductRequest
import io.parrotsoftware.qa_network.domain.responses.ApiProductAvailability
import io.parrotsoftware.qatest.data.datasource.remote.ProductRemoteDataSource
import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.data.toProduct
import io.parrotsoftware.qatest.domain.models.Product
import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun getProducts(
        accessToken: String,
        storeId: String
    ): RepositoryResult<List<Product>> {
        val response = productRemoteDataSource.getProducts(accessToken, storeId)

        if (response.isError)
            return RepositoryResult(
                errorCode = response.requiredError.requiredErrorCode,
                errorMessage = response.requiredError.requiredErrorMessage
            )

        val products = response.requiredResult.results.map { it.toProduct() }
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

        val response = productRemoteDataSource.updateProduct(
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
}