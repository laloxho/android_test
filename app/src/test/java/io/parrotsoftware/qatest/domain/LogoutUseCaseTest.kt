package io.parrotsoftware.qatest.domain

import io.parrotsoftware.qatest.domain.repositories.UserRepository
import io.parrotsoftware.qatest.domain.usescases.LogoutUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class LogoutUseCaseTest {

    @Rule @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var repository: UserRepository
    private lateinit var logoutUseCase: LogoutUseCase

    @Before
    fun setup() {
        logoutUseCase = LogoutUseCase(repository)
    }

    @Test
    fun `Call logout method when invoke use case`() = runTest {
        logoutUseCase.invoke()
        verify(repository).logout()
    }
}
