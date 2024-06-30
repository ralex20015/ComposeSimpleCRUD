package com.earl.simplecrud.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.earl.simplecrud.db.UserRepository

@Composable
fun SignInRoute(
    onSignInSubmitted: (userType: UserRepository.Usuario)-> Unit,
    onSignUpSubmitted: () -> Unit
){
    val signInViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory())

    SignInScreen(modifier = Modifier.padding(16.dp),onSignUp = onSignUpSubmitted, onSignIn = { email, password ->
        signInViewModel.signIn(email,password)
        onSignInSubmitted(signInViewModel.getCurrentUser())
    })
}