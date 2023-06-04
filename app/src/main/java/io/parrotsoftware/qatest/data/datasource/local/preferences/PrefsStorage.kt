package io.parrotsoftware.qatest.data.datasource.local.preferences

interface PrefsStorage {

    fun setString(key: String, value: String)

    fun getString(key: String, default: String = ""): String

    fun getBoolean(key: String): Boolean

    fun setBoolean(key: String, value: Boolean)

    fun getInt(key: String, default: Int = 0): Int

    fun setInt(key: String, value: Int)

    fun clear()
}
