package com.earl.simplecrud.signinsignup

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class SessionRepository {
    private val SESSION_STATE_KEY = stringPreferencesKey("session_state")

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

    object SessionStateSerializer : Serializer<SessionState> {
        override val defaultValue: SessionState
            get() = SessionState()

        override suspend fun readFrom(input: InputStream): SessionState {
            return try {
                Json.decodeFromString(input.bufferedReader().use { it.readText() })
            } catch (e: Exception) {
                defaultValue
            }
        }

        override suspend fun writeTo(t: SessionState, output: OutputStream) {
            output.write(
                Json.encodeToString(t).toByteArray()
            )
        }
    }

    //Aqui guardaria el id del usuario
    suspend fun saveSessionState(context: Context,sessionState: SessionState) {
        context.dataStore.edit { preferences ->
            preferences[SESSION_STATE_KEY] = Json.encodeToString(sessionState)
        }
    }

    fun getSessionState(context: Context): Flow<SessionState> {
        return context.dataStore.data.map { preferences ->
            val sessionStateString = preferences[SESSION_STATE_KEY] ?: ""
            try {
                Json.decodeFromString(sessionStateString)
            } catch (e: Exception) {
                SessionState() // Devuelve el estado por defecto si hay un error
            }
        }
    }
}