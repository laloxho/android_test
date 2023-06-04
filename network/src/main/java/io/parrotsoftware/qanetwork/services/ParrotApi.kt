package io.parrotsoftware.qanetwork.services

import io.parrotsoftware.qanetwork.BuildConfig
import io.parrotsoftware.qanetwork.NetworkBuilder
import io.parrotsoftware.qanetwork.data.requests.ApiAuthRequest
import io.parrotsoftware.qanetwork.data.requests.ApiUpdateProductRequest
import io.parrotsoftware.qanetwork.data.responses.ApiCredentials
import io.parrotsoftware.qanetwork.data.responses.ApiListResponse
import io.parrotsoftware.qanetwork.data.responses.ApiProduct
import io.parrotsoftware.qanetwork.data.responses.ApiSingleResponse
import io.parrotsoftware.qanetwork.data.responses.ApiUserWithStores
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ParrotApiService {

    @POST("/api/auth/token")
    suspend fun auth(
        @Body authRequest: ApiAuthRequest,
    ): ApiCredentials

    @GET("/api/v1/users/me")
    suspend fun getMe(
        @Header("Authorization") access: String,
    ): ApiSingleResponse<ApiUserWithStores>

    @GET("/api/v1/products/")
    suspend fun getProducts(
        @Header("Authorization") access: String,
        @Query("store") storeId: String,
    ): ApiListResponse<ApiProduct>

    @PUT("/api/v1/products/{product_id}/availability")
    suspend fun updateProduct(
        @Header("Authorization") access: String,
        @Path("product_id") productId: String,
        @Body request: ApiUpdateProductRequest,
    ): ApiSingleResponse<ApiProduct>
}

object ParrotApi {

    val service: ParrotApiService by lazy {
        NetworkBuilder.build(BuildConfig.BASE_URL).create(ParrotApiService::class.java)
    }
}
