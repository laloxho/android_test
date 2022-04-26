package io.parrotsoftware.qa_network.domain.requests

import io.parrotsoftware.qa_network.domain.responses.ApiProductAvailability

data class ApiUpdateProductRequest(
    val availability: ApiProductAvailability
)