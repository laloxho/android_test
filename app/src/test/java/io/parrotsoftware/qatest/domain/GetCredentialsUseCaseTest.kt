package io.parrotsoftware.qatest.domain

import io.parrotsoftware.qatest.domain.models.Credentials
import io.parrotsoftware.qatest.domain.usescases.GetCredentialsUseCase
import io.parrotsoftware.qatest.fake.FakeUserRepository
import io.parrotsoftware.qatest.utils.assertIsInstanceOf
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetCredentialsUseCaseTest {

    @Rule @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()
    private lateinit var repository: FakeUserRepository
    private lateinit var getCredentialsUseCase: GetCredentialsUseCase

    @Before
    fun setup() {
        repository = FakeUserRepository()
        getCredentialsUseCase = GetCredentialsUseCase(repository)
    }

    @Test
    fun `Get Credentials instance class when invoke use case`() = runTest {
        val credentials = getCredentialsUseCase.invoke()
        assertIsInstanceOf<Credentials>(credentials.result)
    }

    @Test
    fun `Get Credentials when invoke use case`() = runTest {
        val credentials = getCredentialsUseCase.invoke()
        assertThat(credentials.result?.access, equalTo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6..."))
        assertThat(credentials.result?.refresh, equalTo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjo..."))
    }
}
