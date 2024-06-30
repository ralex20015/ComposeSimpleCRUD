package com.earl.simplecrud.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AdminRoute(){
    val adminViewModel: AdminViewModel = viewModel(factory = AdminViewModelFactory())
    val users = adminViewModel.getAllUsers().collectAsState(initial = emptyList())
    AdminScreen(users = users.value)
}