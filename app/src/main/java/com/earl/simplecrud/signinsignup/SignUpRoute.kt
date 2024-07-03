package com.earl.simplecrud.signinsignup


import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun SignUpRoute(
    onRegisterSubmitted: ()->Unit,
    onNavUp: ()->Unit
){
    val signUpViewModel: SignUpViewModel = viewModel(factory = SignUpViewModelFactory())

    val scope = rememberCoroutineScope()

    val showToast = remember { mutableStateOf(false) } // To manage Toast visibility
    val toastMessage = remember { mutableStateOf("") }

    SignUpScreen(onRegister = { user ->
        scope.launch {
            withContext(Dispatchers.IO) {
                try {
                    signUpViewModel.registerUser(user)
                    withContext(Dispatchers.Main) {
                        toastMessage.value = "Usuario registrado exitosamente"
                        showToast.value = true
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        toastMessage.value = "Error al registrar usuario: ${e.message ?: "Error desconocido"}"
                        showToast.value = true
                    }
                }
            }
        }
    }, onNavUp = onNavUp)

    //Esto es un side effect
    if (showToast.value){
        Toast.makeText(LocalContext.current, toastMessage.value, Toast.LENGTH_SHORT).show()
    }
}