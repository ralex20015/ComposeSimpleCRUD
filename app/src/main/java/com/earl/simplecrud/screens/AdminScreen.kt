package com.earl.simplecrud.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earl.simplecrud.models.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(users: List<User>) {

    //TODO: Aqui voy a hacer la opcion de mostrar todos los usuarios y ademas de que el admin tambien pueda crear notes
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Admin panel")
            })
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
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
    )

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

@Preview
@Composable
fun AdminScreenPreview() {
    AdminScreen(users = emptyList())
}

