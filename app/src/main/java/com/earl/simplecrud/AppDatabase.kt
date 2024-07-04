package com.earl.simplecrud

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.earl.simplecrud.db.NoteDao
import com.earl.simplecrud.db.UserDao
import com.earl.simplecrud.models.Note
import com.earl.simplecrud.models.User

@Database(
    version = 5,
    entities = [User::class, Note::class],
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao
}
