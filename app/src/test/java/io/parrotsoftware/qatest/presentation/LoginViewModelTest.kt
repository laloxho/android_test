package io.parrotsoftware.qatest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.domain.usescases.LoginUseCase
import io.parrotsoftware.qatest.domain.usescases.UserExistsUseCase
import io.parrotsoftware.qatest.presentation.login.LoginViewModel
import io.parrotsoftware.qatest.presentation.login.LoginViewState
import io.parrotsoftware.qatest.rules.CoroutineRule
import io.parrotsoftware.qatest.utils.assertIsInstanceOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class LoginViewModelTest {

    @Rule @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Rule @JvmField
    val testRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi @get:Rule
    var coroutineRule = CoroutineRule()

    @Mock private lateinit var loginUseCase: LoginUseCase

    @Mock private lateinit var userExistsUseCase: UserExistsUseCase
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(loginUseCase, userExistsUseCase)
    }

    @Test
    fun `Call user exists method when invoke init view`() = runTest {
        val result = RepositoryResult(true)

        given(userExistsUseCase()).willReturn(result)
        loginViewModel.initView()
        verify(userExistsUseCase).invoke()
    }

    @Test
    fun `Get LoginSuccess when invoke init view`() = runTest {
        val result = RepositoryResult(true)

        given(userExistsUseCase()).willReturn(result)
        loginViewModel.initView()

        val stateDataSuccess = loginViewModel.getViewState().value
        assertIsInstanceOf<LoginViewState.LoginSuccess>(stateDataSuccess)
    }

    @Test
    fun `Call login use case when invoke on Login Portrait Clicked`() = runTest {
        val result = RepositoryResult<Nothing>()
        val email = "android-challenge@parrotsoftware.io"
        val password = "8mngDhoPcB3ckV7X"

        given(loginUseCase(email, password)).willReturn(result)
        loginViewModel.onLoginPortraitClicked()
        verify(loginUseCase).invoke(email, password)
    }

    @Test
    fun `Get LoginSuccess when invoke on Login Portrait Clicked`() = runTest {
        val result = RepositoryResult<Nothing>()
        val email = "android-challenge@parrotsoftware.io"
        val password = "8mngDhoPcB3ckV7X"
        given(loginUseCase(email, password)).willReturn(result)
        loginViewModel.onLoginPortraitClicked()

        val stateDataSuccess = loginViewModel.getViewState().value
        assertIsInstanceOf<LoginViewState.LoginSuccess>(stateDataSuccess)
    }

    @Test
    fun `Get Idle when invoke navigated method`() = runTest {
        loginViewModel.navigated()

        val stateDataSuccess = loginViewModel.getViewState().value
        assertIsInstanceOf<LoginViewState.Idle>(stateDataSuccess)
    }
}
