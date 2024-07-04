package com.earl.simplecrud.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.earl.simplecrud.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun findByEmailAndPassword(email: String, password: String): User?

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}
