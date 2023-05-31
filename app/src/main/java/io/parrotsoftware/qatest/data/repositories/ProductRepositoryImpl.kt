package io.parrotsoftware.qatest.data.repositories

import io.parrotsoftware.qa_network.domain.requests.ApiUpdateProductRequest
import io.parrotsoftware.qa_network.domain.responses.ApiProductAvailability
import io.parrotsoftware.qa_network.interactors.NetworkInteractor
import io.parrotsoftware.qa_network.services.ParrotApi
import io.parrotsoftware.qatest.data.domain.Category
import io.parrotsoftware.qatest.data.domain.Product
import io.parrotsoftware.qatest.data.domain.RepositoryResult
import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val networkInteractor: NetworkInteractor
) : ProductRepository {

    override suspend fun getProducts(
        accessToken: String,
        storeId: String
    ): RepositoryResult<List<Product>> {
        val response = networkInteractor.safeApiCall {
            ParrotApi.service.getProducts("Bearer $accessToken", storeId)
        }

        if (response.isError)
            return RepositoryResult(
                errorCode = response.requiredError.requiredErrorCode,
                errorMessage = response.requiredError.requiredErrorMessage
            )

        val products = response.requiredResult.results.map {
            Product(
                it.uuid,
                it.name,
                it.description,
                it.imageUrl,
                it.price,
                it.availability == ApiProductAvailability.AVAILABLE,
                Category(it.category.uuid, it.category.name, it.category.sortPosition)
            )
        }
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

        val response = networkInteractor.safeApiCall {
            ParrotApi.service.updateProduct(
                "Bearer $accessToken",
                productId,
                body
            )
        }

        if (response.isError)
            return RepositoryResult(
                errorCode = response.requiredError.requiredErrorCode,
                errorMessage = response.requiredError.requiredErrorMessage
            )

        return RepositoryResult()
    }
}