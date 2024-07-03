package com.earl.simplecrud.signinsignup

import kotlinx.serialization.Serializable

@Serializable
data class SessionState(val isLoggedIn: Boolean = false,
    val userType: UserType = UserType.NONE)

enum class UserType {
    NONE, ADMIN, USER
}
