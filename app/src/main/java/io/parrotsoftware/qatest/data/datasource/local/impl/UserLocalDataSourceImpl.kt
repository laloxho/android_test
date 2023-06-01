package io.parrotsoftware.qatest.data.datasource.local.impl

import io.parrotsoftware.qatest.data.datasource.local.UserLocalDataSource
import io.parrotsoftware.qatest.data.datasource.local.preferences.PrefsStorage
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private val prefsStorage: PrefsStorage) :
    UserLocalDataSource {

    override fun saveCredentials(access: String, refresh: String) {
        prefsStorage.setString(KEY_ACCESS, access)
        prefsStorage.setString(KEY_REFRESH, refresh)
    }

    override fun getAccess() = prefsStorage.getString(KEY_ACCESS)

    override fun getRefresh() = prefsStorage.getString(KEY_REFRESH)

    override fun saveStore(uuid: String, name: String) {
        prefsStorage.setString(KEY_STORE_UUID, uuid)
        prefsStorage.setString(KEY_STORE_NAME, name)
    }

    override fun getStoreUuid() = prefsStorage.getString(KEY_STORE_UUID)

    override fun getStoreName() = prefsStorage.getString(KEY_STORE_NAME)

    override fun isAuth() = prefsStorage.getString(KEY_ACCESS).isNotBlank()

    override fun clearData() {
        prefsStorage.clear()
    }

    private companion object {
        const val KEY_ACCESS = "key_access"
        const val KEY_REFRESH = "key_refresh"
        const val KEY_STORE_UUID = "key_store_uuid"
        const val KEY_STORE_NAME = "key_store_name"
    }
}
