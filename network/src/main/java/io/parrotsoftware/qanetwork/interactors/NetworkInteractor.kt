package io.parrotsoftware.qanetwork.interactors

import io.parrotsoftware.qanetwork.data.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface NetworkInteractor {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> T,
    ): NetworkResult<T>
}
