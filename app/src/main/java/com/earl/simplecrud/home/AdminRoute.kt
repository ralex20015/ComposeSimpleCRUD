package com.earl.simplecrud.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.earl.simplecrud.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AdminRoute(
    onLogoutSubmitted: () -> Unit
) {
    val adminViewModel: AdminViewModel = viewModel(factory = AdminViewModelFactory())
    val users = adminViewModel.getAllUsers().collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    AdminScreen(
        users = users.value,
        onLogout = onLogoutSubmitted,
        onDeleteUser = { user: User ->
            scope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        adminViewModel.deleteUser(user)
                    }catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    )
}