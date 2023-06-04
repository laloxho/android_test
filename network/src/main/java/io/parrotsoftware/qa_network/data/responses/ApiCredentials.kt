package io.parrotsoftware.qa_network.data.responses

import com.squareup.moshi.Json

data class ApiCredentials(
    @Json(name = "refresh") val refreshToken: String,
    @Json(name = "access") val accessToken: String,
)
