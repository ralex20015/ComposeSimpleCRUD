package com.earl.simplecrud.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInRoute(
    onSignUpSubmitted: () -> Unit
){
    val SignInViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory())

    SignInScreen(onSignUp = onSignUpSubmitted, onSignIn = {})
}