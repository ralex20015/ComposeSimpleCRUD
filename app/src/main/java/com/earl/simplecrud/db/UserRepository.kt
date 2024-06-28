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
}