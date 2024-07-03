package com.earl.simplecrud.signinsignup

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SignInRoute(
    signInViewModel: SignInViewModel,
    onSignInSubmitted: (userType: UserType) -> Unit,
    onSignUpSubmitted: () -> Unit
) {
    val context = LocalContext.current
    val user = signInViewModel.sessionState.collectAsState()
    if (!user.value.isLoggedIn) {
        SignInScreen(
            modifier = Modifier.padding(16.dp),
            onSignUp = onSignUpSubmitted,
            onSignIn = { email, password ->
                signInViewModel.login(email, password, context)
                onSignInSubmitted(user.value.userType)
            })
    }
}