package io.parrotsoftware.qatest.domain

import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.domain.repositories.UserRepository
import io.parrotsoftware.qatest.domain.usescases.LoginUseCase
import io.parrotsoftware.qatest.utils.assertIsInstanceOf
import io.parrotsoftware.qatest.utils.mock
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class LoginUseCaseTest {

    @Rule @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var repository: UserRepository
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        loginUseCase = LoginUseCase(repository)
    }

    @Test
    fun `Call login method when invoke use case`() = runTest {
        val email = "some_email"
        val password = "some_password"
        loginUseCase.invoke(email, password)
        verify(repository).login(email, password)
    }

    @Test
    fun `Get login result when invoke use case`() = runTest {
        val result = mock<RepositoryResult<Nothing>>()
        val email = "some_email"
        val password = "some_password"

        given(repository.login(email, password)).willReturn(result)
        val login = loginUseCase.invoke(email, password)
        assertThat(result, equalTo(login))
        assertIsInstanceOf<RepositoryResult<Nothing>>(login)
    }
}
