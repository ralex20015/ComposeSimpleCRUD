package com.earl.simplecrud.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.earl.simplecrud.models.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onRegister: (user: User) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    //Este sobrevive death process me parece y activity recreation
    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }
    //TODO: Implement navigation on the appbar
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "SignUp")
            })
        }, content = { innerPadding ->

            //TODO: See a way to change this because im almost writing the same code
            Column(modifier = modifier.padding(innerPadding)) {
                OutlinedTextField(value = name, placeholder = {
                    Text("Ingrese el nombre")
                },
                    label = {
                        Text(text = "Nombre")
                    },
                    onValueChange = { name = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(value = lastName, placeholder = {
                    Text("Ingrese el apellido")
                },
                    label = {
                        Text(text = "Apellido")
                    },
                    onValueChange = { lastName = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(value = email, placeholder = {
                    Text("Ingrese el correo")
                },
                    label = {
                        Text(text = "Email")
                    },
                    onValueChange = { email = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(value = password, placeholder = {
                    Text("Ingrese la contrasenia")
                },
                    label = {
                        Text(text = "Password")
                    },
                    onValueChange = { password = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(value = phoneNumber, placeholder = {
                    Text("Ingrese el numero de telefono")
                },
                    label = {
                        Text(text = "Telefono")
                    },
                    onValueChange = { phoneNumber = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = modifier,
                    onClick = {
                        onRegister(User(0, name, lastName,phoneNumber, email, password))
                    }
                ) {
                    Text(text = "Registrar")
                }

            }
        })
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(modifier = Modifier.fillMaxWidth(), {} )
}