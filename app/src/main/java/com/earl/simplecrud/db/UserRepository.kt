package com.earl.simplecrud.db


import com.earl.simplecrud.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException



class UserRepository private constructor() {

    private val userDao: UserDao = DatabaseProvider.database.userDao()

    companion object {
        private var INSTANCE: UserRepository? = null

        fun get(): UserRepository {
            if (INSTANCE == null) {
                INSTANCE = UserRepository()
            }
            return INSTANCE ?: throw IllegalArgumentException("UserRepository must be initialized")
        }
    }



    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAll()
    }

    suspend fun insertAll(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insertAll(user)
        }
    }

    suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.delete(user)
        }
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        return withContext(Dispatchers.IO) {
           userDao.findByEmailAndPassword(email, password)
        }
    }
}