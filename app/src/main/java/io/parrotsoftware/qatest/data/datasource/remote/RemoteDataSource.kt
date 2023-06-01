package io.parrotsoftware.qatest.data.datasource.remote

import io.parrotsoftware.qa_network.data.NetworkResult
import io.parrotsoftware.qa_network.data.requests.ApiUpdateProductRequest
import io.parrotsoftware.qa_network.data.responses.ApiCredentials
import io.parrotsoftware.qa_network.data.responses.ApiListResponse
import io.parrotsoftware.qa_network.data.responses.ApiProduct
import io.parrotsoftware.qa_network.data.responses.ApiSingleResponse
import io.parrotsoftware.qa_network.data.responses.ApiUserWithStores

interface RemoteDataSource {

    suspend fun getProducts(accessToken: String, storeId: String): NetworkResult<ApiListResponse<ApiProduct>>

    suspend fun updateProduct(
        accessToken: String,
        productId: String,
        body: ApiUpdateProductRequest
    ): NetworkResult<ApiSingleResponse<ApiProduct>>

    suspend fun auth(accessToken: String, storeId: String): NetworkResult<ApiCredentials>

    suspend fun getMe(accessToken: String): NetworkResult<ApiSingleResponse<ApiUserWithStores>>
}
