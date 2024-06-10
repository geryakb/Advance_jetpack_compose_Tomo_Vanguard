package com.example.tomovanguard_tugas.SharedPreferences

import android.content.Context
import android.util.Log
import com.example.tomovanguard_tugas.SharedPreferences.PreferencesKey.NAME_KEY
import com.example.tomovanguard_tugas.SharedPreferences.PreferencesKey.NAME_PREF
import com.example.tomovanguard_tugas.SharedPreferences.PreferencesKey.PASSWORD_KEY

class SharedPreferencesManager(context: Context) {
    private val preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    var name
        get() = preferences.getString(NAME_KEY, "")
        set(value) {
            editor.putString(NAME_KEY, value)
            Log.d("Preferences", ": $value")
            editor.commit()
        }

    var password
        get() = preferences.getString(PASSWORD_KEY, "")
        set(value) {
            editor.putString(PASSWORD_KEY, value)
            editor.commit()
        }

    fun clear() {
        editor.clear()
        editor.commit()
    }
}