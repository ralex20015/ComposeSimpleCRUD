package com.earl.simplecrud.signinsignup


import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignUpRoute(
    onRegisterSubmitted: (isRegistered: Boolean)->Unit,
    onNavUp: ()->Unit
){
    val signUpViewModel: SignUpViewModel = viewModel(factory = SignUpViewModelFactory())

    val showToast = remember { mutableStateOf(false) } // To manage Toast visibility
    val toastMessage = remember { mutableStateOf("") }

    SignUpScreen(onRegister = { user ->
        try {
            signUpViewModel.registerUser(user)
            toastMessage.value = "Usuario registrado exitosamente"
            onRegisterSubmitted(true)
            showToast.value = true
        }catch (e: Exception){
            toastMessage.value = "Error al registrar usuario: ${e.message ?: "Error desconocido"}"
            onRegisterSubmitted(false)
            showToast.value = true
        }
    }, onNavUp = onNavUp)

    //Esto es un side effect
    if (showToast.value){
        Toast.makeText(LocalContext.current, toastMessage.value, Toast.LENGTH_SHORT).show()
        showToast.value = false
    }
}