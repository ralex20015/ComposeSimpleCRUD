package com.earl.simplecrud

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.earl.simplecrud.db.UserDao
import com.earl.simplecrud.models.User

@Database(
    version = 4,
    entities = [User::class],
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
