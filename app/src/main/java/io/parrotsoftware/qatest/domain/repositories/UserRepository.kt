package io.parrotsoftware.qatest.domain.repositories

import io.parrotsoftware.qatest.domain.models.Credentials
import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.domain.models.Store

interface UserRepository {

    suspend fun login(email: String, password: String): RepositoryResult<Nothing>

    suspend fun userExists(): RepositoryResult<Boolean>

    suspend fun getCredentials(): RepositoryResult<Credentials>

    suspend fun getStore(): RepositoryResult<Store>

}