package com.earl.simplecrud

import kotlinx.coroutines.flow.Flow

class UserRepository(private val database: AppDatabase) {
    val userDao: UserDao = database.userDao()

    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAll()
    }



    suspend fun insertAll(user: User) {
        userDao.insertAll(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.delete(user)
    }

}