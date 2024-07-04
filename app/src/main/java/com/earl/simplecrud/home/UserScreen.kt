package com.earl.simplecrud.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    onLogoutSubmitted: () -> Unit = {},
    onAddSubmitted: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("User Home") },
                navigationIcon = {
                    IconButton(onClick = onLogoutSubmitted) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Log out"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddSubmitted) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add note")
            }
        }
    ) { innerPadding ->
        innerPadding.apply {
            PaddingValues(16.dp)
        }
        //TODO: Show the list of notes
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("Here we are")
        }
    }
}

@Preview
@Composable
fun UserScreenPreview() {
    UserScreen()
}