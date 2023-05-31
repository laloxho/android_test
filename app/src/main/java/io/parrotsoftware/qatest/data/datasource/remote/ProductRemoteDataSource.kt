package io.parrotsoftware.qatest.data.datasource.remote

import io.parrotsoftware.qa_network.domain.NetworkResult
import io.parrotsoftware.qa_network.domain.requests.ApiUpdateProductRequest
import io.parrotsoftware.qa_network.domain.responses.ApiListResponse
import io.parrotsoftware.qa_network.domain.responses.ApiProduct
import io.parrotsoftware.qa_network.domain.responses.ApiSingleResponse

interface ProductRemoteDataSource {

    suspend fun getProducts(accessToken: String, storeId: String): NetworkResult<ApiListResponse<ApiProduct>>

    suspend fun updateProduct(
        accessToken: String,
        productId: String,
        body: ApiUpdateProductRequest
    ): NetworkResult<ApiSingleResponse<ApiProduct>>
}
