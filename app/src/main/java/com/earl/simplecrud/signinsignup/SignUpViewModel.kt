package com.earl.simplecrud.signinsignup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.earl.simplecrud.db.UserRepository
import com.earl.simplecrud.models.User
import kotlinx.coroutines.launch

class SignUpViewModel(private val userRepository: UserRepository): ViewModel() {

   fun registerUser(user: User){
        viewModelScope.launch {
            userRepository.insertAll(user)
        }
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