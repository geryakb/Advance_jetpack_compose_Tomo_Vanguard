package com.example.tomovanguard_tugas.DataStore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.tomovanguard_tugas.SharedPreferences.PreferencesKey.STATUS_LOGIN_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("StatusLogin")
    }

    val getStatusLogin: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[STATUS_LOGIN_KEY] ?: false
    }

    suspend fun saveStatus(isLogin: Boolean) = context.dataStore.edit { preferences ->
        preferences[STATUS_LOGIN_KEY] = isLogin
        Log.d("Save", "SaveStatus: $isLogin")
    }

    suspend fun clearStatus() = context.dataStore.edit { preferences ->
        preferences.remove(STATUS_LOGIN_KEY)
        Log.d("Clear", "clearStatus: $STATUS_LOGIN_KEY")
    }
}