package io.parrotsoftware.qatest.data.datasource.remote.impl

import io.parrotsoftware.qa_network.domain.requests.ApiAuthRequest
import io.parrotsoftware.qa_network.interactors.NetworkInteractor
import io.parrotsoftware.qa_network.services.ParrotApi
import io.parrotsoftware.qatest.data.datasource.remote.UserRemoteDataSource
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val networkInteractor: NetworkInteractor
): UserRemoteDataSource {

    override suspend fun auth(accessToken: String, storeId: String) =
        networkInteractor.safeApiCall {
            ParrotApi.service.auth(ApiAuthRequest(accessToken, storeId))
        }

    override suspend fun getMe(accessToken: String) =
        networkInteractor.safeApiCall {
            ParrotApi.service.getMe("Bearer $accessToken")
        }
}
