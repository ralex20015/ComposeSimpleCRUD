package com.earl.simplecrud.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.earl.simplecrud.db.UserRepository
import com.earl.simplecrud.models.User

class SignUpViewModel(private val userRepository: UserRepository): ViewModel() {

    suspend fun registerUser(user: User){
        userRepository.insertAll(user)
    }
}

class SignUpViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(UserRepository.get()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}