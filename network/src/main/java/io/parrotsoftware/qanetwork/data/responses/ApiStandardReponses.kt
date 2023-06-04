package io.parrotsoftware.qanetwork.data.responses

data class ApiListResponse<T>(
    val status: String,
    val results: List<T>,
)

data class ApiSingleResponse<T>(
    val status: String,
    val result: T,
)
