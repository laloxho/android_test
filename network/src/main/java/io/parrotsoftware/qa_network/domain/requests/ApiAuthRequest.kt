package io.parrotsoftware.qa_network.domain.requests

data class ApiAuthRequest(
    val email: String,
    val password: String
)