package com.example.vibestore.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.vibestore.model.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sessions")

class SessionPreferences private constructor(
    private val dataStore: DataStore<Preferences>
){
    suspend fun saveLoginData(username: String, token: String) {
        dataStore.edit { preferences ->
            preferences[KEY_USERNAME] = username
            preferences[KEY_TOKEN] = token
        }
    }

    fun getUsername() : Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[KEY_USERNAME] ?: "User"
        }
    }

    fun getSession() : Flow<LoginResponse> {
        return dataStore.data.map { preferences ->
            LoginResponse(
                preferences[KEY_TOKEN] ?: ""
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SessionPreferences? = null

        private val KEY_USERNAME = stringPreferencesKey("username")
        private val KEY_TOKEN = stringPreferencesKey("token")

        fun getInstance(dataStore: DataStore<Preferences>) : SessionPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SessionPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}