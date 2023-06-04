package io.parrotsoftware.qatest.data.repositories

import io.parrotsoftware.qatest.data.datasource.local.LocalDataSource
import io.parrotsoftware.qatest.data.datasource.remote.RemoteDataSource
import io.parrotsoftware.qatest.domain.models.Credentials
import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.domain.models.Store
import io.parrotsoftware.qatest.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : UserRepository {

    override suspend fun login(email: String, password: String): RepositoryResult<Nothing> {
        val responseAuth = remoteDataSource.auth(email, password)
        if (responseAuth.isError) {
            return RepositoryResult(
                errorCode = responseAuth.requiredError.requiredErrorCode,
                errorMessage = responseAuth.requiredError.requiredErrorMessage,
            )
        }

        val accessToken = responseAuth.requiredResult.accessToken
        val responseUser = remoteDataSource.getMe(accessToken)

        if (responseUser.isError) {
            return RepositoryResult(
                errorCode = responseUser.requiredError.requiredErrorCode,
                errorMessage = responseUser.requiredError.requiredErrorMessage,
            )
        }

        if (responseUser.requiredResult.result.stores.isEmpty()) {
            return RepositoryResult(
                errorCode = "",
                errorMessage = "Store Not Found",
            )
        }

        val apiCredentials = responseAuth.requiredResult
        val apiUser = responseUser.requiredResult.result
        val apiStore = apiUser.stores.first()

        localDataSource.saveCredentials(apiCredentials.accessToken, apiCredentials.refreshToken)
        localDataSource.saveStore(apiStore.uuid, apiStore.name)

        return RepositoryResult()
    }

    override suspend fun userExists(): RepositoryResult<Boolean> {
        return RepositoryResult(localDataSource.isAuth())
    }

    override suspend fun getCredentials(): RepositoryResult<Credentials> {
        return RepositoryResult(
            Credentials(
                localDataSource.getAccess(),
                localDataSource.getRefresh(),
            ),
        )
    }

    override suspend fun getStore(): RepositoryResult<Store> {
        return RepositoryResult(
            Store(
                localDataSource.getStoreUuid(),
                localDataSource.getStoreName(),
            ),
        )
    }

    override suspend fun logout() {
        localDataSource.clearData()
    }
}
