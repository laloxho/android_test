package io.parrotsoftware.qatest.data.datasource.remote

import io.parrotsoftware.qa_network.domain.NetworkResult
import io.parrotsoftware.qa_network.domain.responses.ApiCredentials
import io.parrotsoftware.qa_network.domain.responses.ApiSingleResponse
import io.parrotsoftware.qa_network.domain.responses.ApiUserWithStores

interface UserRemoteDataSource {

    suspend fun auth(accessToken: String, storeId: String): NetworkResult<ApiCredentials>

    suspend fun getMe(accessToken: String): NetworkResult<ApiSingleResponse<ApiUserWithStores>>
}
