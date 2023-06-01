package io.parrotsoftware.qatest.fake

import io.parrotsoftware.qatest.data.datasource.local.preferences.PrefsStorage

class FakePrefsStorage: PrefsStorage {

    private val values = mutableMapOf<String, Any>()

    override fun setString(key: String, value: String) {
        values[key] = value
    }

    override fun getString(key: String, default: String): String {
        return values[key] as? String ?: ""
    }

    override fun getBoolean(key: String): Boolean {
        return values[key] as? Boolean ?: false
    }

    override fun setBoolean(key: String, value: Boolean) {
        values[key] = value
    }

    override fun getInt(key: String, default: Int): Int {
        return values[key] as? Int ?: 0
    }

    override fun setInt(key: String, value: Int) {
        values[key] = value
    }

    override fun clear() {
        values.clear()
    }
}
