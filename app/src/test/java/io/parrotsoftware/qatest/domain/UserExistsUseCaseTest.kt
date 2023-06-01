package io.parrotsoftware.qatest.domain

import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.domain.repositories.UserRepository
import io.parrotsoftware.qatest.domain.usescases.UserExistsUseCase
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

class UserExistsUseCaseTest {

    @Rule @JvmField val mockitoRule: MockitoRule = MockitoJUnit.rule()
    @Mock private lateinit var repository: UserRepository
    private lateinit var userExistsUseCase: UserExistsUseCase

    @Before
    fun setup() {
        userExistsUseCase = UserExistsUseCase(repository)
    }

    @Test
    fun `Call user exists method when invoke use case`() = runTest {
        val result = mock<RepositoryResult<Boolean>>()
        given(repository.userExists()).willReturn(result)
        userExistsUseCase.invoke()
        verify(repository).userExists()
    }

    @Test
    fun `Get user exists true when invoke use case`() = runTest {
        val result = RepositoryResult(true)
        given(repository.userExists()).willReturn(result)
        val isUserExists = userExistsUseCase.invoke()
        assertThat(isUserExists.result, equalTo(true))
    }
}
