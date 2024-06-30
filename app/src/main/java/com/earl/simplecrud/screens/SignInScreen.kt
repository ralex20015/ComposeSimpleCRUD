package com.earl.simplecrud.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onSignUp: () -> Unit,
    onSignIn: (email: String, password: String) -> Unit
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val takeAll = Modifier.fillMaxWidth()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Sign In")
            }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email, onValueChange = {
                        email = it
                    },
                    modifier = takeAll
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password, onValueChange = {
                        password = it
                    },
                    modifier = takeAll
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "¿Aun no estas registrado?",
                    modifier = Modifier.clickable(onClick = onSignUp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    onSignIn(email, password)
                }, modifier = takeAll) {
                    Text(text = "Iniciar sesion")
                }
            }
        }
    )
}

@Preview
@Composable
fun SignInPreview() {
    SignInScreen(Modifier.padding(16.dp), {}, {email, password ->  })
}