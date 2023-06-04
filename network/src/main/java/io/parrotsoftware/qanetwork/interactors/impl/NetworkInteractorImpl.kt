package io.parrotsoftware.qanetwork.interactors.impl

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.parrotsoftware.qanetwork.data.APIError
import io.parrotsoftware.qanetwork.data.NetworkError
import io.parrotsoftware.qanetwork.data.NetworkErrorType
import io.parrotsoftware.qanetwork.data.NetworkResult
import io.parrotsoftware.qanetwork.interactors.NetworkInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NetworkInteractorImpl @Inject constructor() : NetworkInteractor {

    override suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T,
    ): NetworkResult<T> = withContext(dispatcher) {
        try {
            val response = apiCall.invoke()
            NetworkResult(result = response)
        } catch (throwable: Throwable) {
            NetworkResult(networkError = createError(throwable))
        }
    }

    private fun createError(throwable: Throwable): NetworkError {
        return when (throwable) {
            is IOException -> {
                NetworkError(
                    NetworkErrorType.CONNECTION_ERROR,
                    throwable.message,
                    NetworkErrorType.CONNECTION_ERROR.name,
                )
            }
            is HttpException -> {
                val bodyResponse: String? = throwable.response()?.errorBody()?.string()
                val apiError = parseErrorBody(bodyResponse)
                val codeError = apiError?.code
                NetworkError(
                    NetworkErrorType.API_ERROR,
                    bodyResponse,
                    codeError ?: throwable.code().toString(),
                    apiError,
                )
            }
            is JsonDataException -> {
                NetworkError(NetworkErrorType.API_ERROR, throwable.message)
            }
            else -> {
                NetworkError(NetworkErrorType.UNKNOWN_ERROR, throwable.message)
            }
        }
    }

    private fun parseErrorBody(bodyResponse: String?): APIError? {
        return try {
            bodyResponse?.let {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val jsonAdapter: JsonAdapter<APIError> = moshi.adapter(APIError::class.java)
                return jsonAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }
}
