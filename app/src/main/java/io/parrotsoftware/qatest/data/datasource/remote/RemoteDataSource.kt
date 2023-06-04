package io.parrotsoftware.qatest.data.datasource.remote

import io.parrotsoftware.qanetwork.data.NetworkResult
import io.parrotsoftware.qanetwork.data.requests.ApiUpdateProductRequest
import io.parrotsoftware.qanetwork.data.responses.ApiCredentials
import io.parrotsoftware.qanetwork.data.responses.ApiListResponse
import io.parrotsoftware.qanetwork.data.responses.ApiProduct
import io.parrotsoftware.qanetwork.data.responses.ApiSingleResponse
import io.parrotsoftware.qanetwork.data.responses.ApiUserWithStores

interface RemoteDataSource {

    suspend fun getProducts(accessToken: String, storeId: String): NetworkResult<ApiListResponse<ApiProduct>>

    suspend fun updateProduct(
        accessToken: String,
        productId: String,
        body: ApiUpdateProductRequest,
    ): NetworkResult<ApiSingleResponse<ApiProduct>>

    suspend fun auth(email: String, password: String): NetworkResult<ApiCredentials>

    suspend fun getMe(accessToken: String): NetworkResult<ApiSingleResponse<ApiUserWithStores>>
}
