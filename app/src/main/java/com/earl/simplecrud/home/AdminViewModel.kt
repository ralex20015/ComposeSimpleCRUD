package com.earl.simplecrud.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.earl.simplecrud.db.UserRepository
import com.earl.simplecrud.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AdminViewModel(private val userRepository: UserRepository): ViewModel() {

    fun getAllUsers(): Flow<List<User>> {
        return userRepository.getAllUsers()
    }
    //TODO: Add method for delete users

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
        Log.d("AdminViewModel", "User deleted: $user")
    }
}

class AdminViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminViewModel::class.java)) {
            return AdminViewModel(UserRepository.get()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}