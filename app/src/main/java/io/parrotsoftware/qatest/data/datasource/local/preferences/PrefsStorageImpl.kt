package io.parrotsoftware.qatest.data.datasource.local.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PrefsStorageImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    PrefsStorage {

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String, default: String): String {
        return sharedPreferences.getString(key, default)!!
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun setBoolean(key: String, value: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    override fun getInt(key: String, default: Int): Int {
        return sharedPreferences.getInt(key, default)
    }

    override fun setInt(key: String, value: Int) {
        with(sharedPreferences.edit()) {
            putInt(key, value)
            apply()
        }
    }

    override fun clear() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
}
