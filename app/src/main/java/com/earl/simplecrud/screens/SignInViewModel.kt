package com.earl.simplecrud.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.earl.simplecrud.db.UserRepository

class SignInViewModel(private val userRepository: UserRepository): ViewModel(){

    fun login(email: String, password: String){

    }

}

class SignInViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(UserRepository.get()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}