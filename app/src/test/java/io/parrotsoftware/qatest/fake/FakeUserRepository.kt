package io.parrotsoftware.qatest.fake

import io.parrotsoftware.qatest.fake.FakeData.givenCredentials
import io.parrotsoftware.qatest.fake.FakeData.givenStore
import io.parrotsoftware.qatest.domain.models.Credentials
import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.domain.models.Store
import io.parrotsoftware.qatest.domain.repositories.UserRepository

class FakeUserRepository : UserRepository {

    override suspend fun login(email: String, password: String): RepositoryResult<Nothing> {
        return RepositoryResult()
    }

    override suspend fun userExists(): RepositoryResult<Boolean> {
        return RepositoryResult(true)
    }

    override suspend fun getCredentials(): RepositoryResult<Credentials> {
        val credentials = givenCredentials()
        return RepositoryResult(credentials)
    }

    override suspend fun getStore(): RepositoryResult<Store> {
        val store = givenStore()
        return RepositoryResult(store)
    }
}
