package io.parrotsoftware.qanetwork.data.requests

import io.parrotsoftware.qanetwork.data.responses.ApiProductAvailability

data class ApiUpdateProductRequest(
    val availability: ApiProductAvailability,
)
