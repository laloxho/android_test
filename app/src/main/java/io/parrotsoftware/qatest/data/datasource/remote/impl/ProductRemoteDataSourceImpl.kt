package io.parrotsoftware.qatest.data.datasource.remote.impl

import io.parrotsoftware.qa_network.domain.requests.ApiUpdateProductRequest
import io.parrotsoftware.qa_network.interactors.NetworkInteractor
import io.parrotsoftware.qa_network.services.ParrotApi
import io.parrotsoftware.qatest.data.datasource.remote.ProductRemoteDataSource
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(
    private val networkInteractor: NetworkInteractor
): ProductRemoteDataSource {

    override suspend fun getProducts(accessToken: String, storeId: String) =
        networkInteractor.safeApiCall {
            ParrotApi.service.getProducts("Bearer $accessToken", storeId)
        }

    override suspend fun updateProduct(
        accessToken: String,
        productId: String,
        body: ApiUpdateProductRequest
    ) =
        networkInteractor.safeApiCall {
            ParrotApi.service.updateProduct(
                "Bearer $accessToken",
                productId,
                body
            )
        }
}
