package com.earl.simplecrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.earl.simplecrud.ui.theme.SimpleCRUDTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earl.simplecrud.models.User
import com.earl.simplecrud.db.UserRepository
import com.earl.simplecrud.screens.SignUpRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleCRUDTheme {
                SignUpRoute()
            }
        }
    }
}


@Composable
fun Register(users: List<User>, modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var lastName by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier.padding(PaddingValues(16.dp))) {
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
        LazyColumn {
            items(users) {
                ListItem(
                    user = it, modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                )
            }
        }
    }


}

//Se le pasan callbacks acuerdate de eso
@Composable
fun ListItem(user: User, modifier: Modifier) {
    Card(modifier = modifier.padding(PaddingValues(bottom = 8.dp))) {
        Column(modifier = Modifier.padding(4.dp)) {
            Row {
                Column(modifier = Modifier.weight(3f)) {
                    Text(
                        text = "${user.firstName}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${user.lastName}")
                }

                Button(onClick = { }, modifier = Modifier.weight(1f)) {
                    Text(text = "Delete")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleCRUDTheme {
    }
}