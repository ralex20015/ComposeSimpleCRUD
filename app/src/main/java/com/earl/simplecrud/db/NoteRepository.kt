package com.earl.simplecrud.db

import com.earl.simplecrud.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NoteRepository private constructor() {
    private val noteDao: NoteDao = DatabaseProvider.database.noteDao()

    companion object {
        private var INSTANCE: NoteRepository? = null
        fun get(): NoteRepository {
            if (INSTANCE == null) {
                INSTANCE = NoteRepository()
            }
            return INSTANCE ?: throw IllegalStateException("NoteRepository must be initialized")
        }
    }

    fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAll()
    }

    suspend fun insertNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insertNote(note)
        }
    }

    suspend fun deleteNote(note: Note){
        withContext(Dispatchers.IO){
            noteDao.deleteNote(note)
        }
    }

    suspend fun updateNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.updateNote(note)
        }
    }
}