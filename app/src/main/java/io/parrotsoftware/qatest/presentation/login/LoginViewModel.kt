package io.parrotsoftware.qatest.presentation.login

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.parrotsoftware.qatest.domain.usescases.LoginUseCase
import io.parrotsoftware.qatest.domain.usescases.UserExistsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userExistsUseCase: UserExistsUseCase,
) : ViewModel(), LifecycleObserver {

    private val viewState = MutableLiveData<LoginViewState>()
    fun getViewState() = viewState

    val email = MutableLiveData("android-challenge@parrotsoftware.io")
    val password = MutableLiveData("8mngDhoPcB3ckV7X")

    fun initView() {
        viewModelScope.launch {
            val response = userExistsUseCase()
            if (response.isError) {
                viewState.value = LoginViewState.LoginError
                return@launch
            }

            if (response.requiredResult) {
                viewState.value = LoginViewState.LoginSuccess
            }
        }
    }

    fun onLoginPortraitClicked() {
        viewModelScope.launch {
            val response = loginUseCase.invoke(email.value.orEmpty(), password.value.orEmpty())
            if (response.isError) {
                viewState.value = LoginViewState.LoginError
            } else {
                viewState.value = LoginViewState.LoginSuccess
            }
        }
    }

    fun navigated() {
        viewState.value = LoginViewState.Idle
    }
}
