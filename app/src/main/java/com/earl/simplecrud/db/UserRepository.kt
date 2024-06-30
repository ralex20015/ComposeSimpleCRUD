package com.earl.simplecrud.db

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.earl.simplecrud.AppDatabase
import com.earl.simplecrud.models.User
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException

private const val DB_NAME = "database-name"


class UserRepository private constructor(context: Context) {

    sealed class Usuario {

        data class LoggedInUser(val email: String): Usuario()
        object AdminUser: Usuario()
        object NoUserLoggedIn : Usuario()
    }

    private var _user: Usuario = Usuario.NoUserLoggedIn
    val user: Usuario
        get() = _user

    private val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE User ADD number TEXT")
        }
    }
    private val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE User ADD email TEXT")
            db.execSQL("ALTER TABLE User ADD password TEXT")
        }
    }

    private val database: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_2_3, MIGRATION_3_4)
        .build()


    //TODO Hacer con sharedPreferences la simulacion del inicio de sesion

    companion object {
        private var INSTANCE: UserRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = UserRepository(context)
            }
        }

        fun get(): UserRepository {
            return INSTANCE ?: throw IllegalArgumentException("UserRepository must be initialized")
        }
    }

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

    fun signIn(email: String, password: String) {
        when{
            email == "admin" && password == "12345" -> _user = Usuario.AdminUser
            else -> Usuario.NoUserLoggedIn
        }
    }
}