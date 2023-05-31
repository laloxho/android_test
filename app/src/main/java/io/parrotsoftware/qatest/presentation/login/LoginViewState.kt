package io.parrotsoftware.qatest.presentation.login

sealed class LoginViewState {

    object Idle: LoginViewState()
    object LoginError: LoginViewState()
    object LoginSuccess: LoginViewState()

}