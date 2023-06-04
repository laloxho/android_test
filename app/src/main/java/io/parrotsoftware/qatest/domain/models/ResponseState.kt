package io.parrotsoftware.qatest.domain.models

sealed class ResponseState {

    object Loading : ResponseState()

    data class Success(val data: Any?) : ResponseState() { inline fun <reified T> responseTo() = data as T }
}
