package com.earl.simplecrud.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.earl.simplecrud.db.UserRepository
import com.earl.simplecrud.models.User
import kotlinx.coroutines.flow.Flow

class AdminViewModel(private val userRepository: UserRepository): ViewModel() {

    //TODO: Add method for delete users

    fun getAllUsers(): Flow<List<User>> {
        return userRepository.getAllUsers()
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