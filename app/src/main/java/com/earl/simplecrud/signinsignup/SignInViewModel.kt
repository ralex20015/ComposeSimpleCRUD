package com.earl.simplecrud.signinsignup

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.earl.simplecrud.MyApplication
import com.earl.simplecrud.db.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val userRepository: UserRepository,
    application: MyApplication
) : AndroidViewModel(application) {
    private val sessionRepository = SessionRepository()

    private val _sessionState = MutableStateFlow(SessionState())
    val sessionState: StateFlow<SessionState> = _sessionState.asStateFlow()

    init {
        viewModelScope.launch {
            sessionRepository.getSessionState(getApplication()).collect {
                _sessionState.value = it
            }
        }
    }

    fun login(email: String, password: String, context: Context) {
        viewModelScope.launch {
            //a
            if (email == "admin" && password == "12345") {
                val newSessionState = SessionState(
                    isLoggedIn =
                    true, userType = UserType.ADMIN
                )
                //Escribimos en el archivo DataStore para guardar la sesion
                sessionRepository.saveSessionState(context, newSessionState)
                _sessionState.value = newSessionState
            } else {
                //handle if user exists on db
                val newSessionState = SessionState(
                    isLoggedIn =
                    true, userType = UserType.USER
                )
                sessionRepository.saveSessionState(context, newSessionState)
                _sessionState.value = newSessionState
            }
        }
    }

    private suspend fun updateSessionState(context: Context, newSessionState: SessionState) {
        sessionRepository.saveSessionState(context, newSessionState)
        _sessionState.value = newSessionState
    }

    fun logout(context: Context) {
        viewModelScope.launch {
            sessionRepository.saveSessionState(
                context,
                SessionState()
            ) // Guarda un estado vacío para cerrar sesión
            _sessionState.value = SessionState()
        }
    }
}

class SignInViewModelFactory(private val application: MyApplication) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(UserRepository.get(), application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}