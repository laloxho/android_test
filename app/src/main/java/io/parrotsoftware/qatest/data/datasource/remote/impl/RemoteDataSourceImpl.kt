package io.parrotsoftware.qatest.data.datasource.remote.impl

import io.parrotsoftware.qanetwork.data.requests.ApiAuthRequest
import io.parrotsoftware.qanetwork.data.requests.ApiUpdateProductRequest
import io.parrotsoftware.qanetwork.interactors.NetworkInteractor
import io.parrotsoftware.qanetwork.services.ParrotApi
import io.parrotsoftware.qatest.data.datasource.remote.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val networkInteractor: NetworkInteractor,
) : RemoteDataSource {

    override suspend fun getProducts(accessToken: String, storeId: String) =
        networkInteractor.safeApiCall {
            ParrotApi.service.getProducts("Bearer $accessToken", storeId)
        }

    override suspend fun updateProduct(
        accessToken: String,
        productId: String,
        body: ApiUpdateProductRequest,
    ) =
        networkInteractor.safeApiCall {
            ParrotApi.service.updateProduct(
                "Bearer $accessToken",
                productId,
                body,
            )
        }

    override suspend fun auth(email: String, password: String) =
        networkInteractor.safeApiCall {
            ParrotApi.service.auth(ApiAuthRequest(email, password))
        }

    override suspend fun getMe(accessToken: String) =
        networkInteractor.safeApiCall {
            ParrotApi.service.getMe("Bearer $accessToken")
        }
}
