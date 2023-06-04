package io.parrotsoftware.qanetwork.data.responses

data class ApiStore(
    val uuid: String,
    val name: String,
)

data class ApiUserWithStores(
    val uuid: String,
    val email: String,
    val stores: List<ApiStore>,
)
