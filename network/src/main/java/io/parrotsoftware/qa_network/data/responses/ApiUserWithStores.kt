package io.parrotsoftware.qa_network.data.responses

data class ApiStore(
    val uuid: String,
    val name: String,
)

data class ApiUserWithStores(
    val uuid: String,
    val email: String,
    val stores: List<ApiStore>,
)
