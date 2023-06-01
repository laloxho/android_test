package io.parrotsoftware.qa_network.data.requests

import io.parrotsoftware.qa_network.data.responses.ApiProductAvailability

data class ApiUpdateProductRequest(
    val availability: ApiProductAvailability
)