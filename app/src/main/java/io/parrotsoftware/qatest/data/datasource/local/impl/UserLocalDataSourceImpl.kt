package io.parrotsoftware.qatest.data.datasource.local.impl

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.parrotsoftware.qatest.data.datasource.local.UserLocalDataSource
import io.parrotsoftware.qatest.data.datasource.local.preferences.PrefsStorage
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(@ApplicationContext ctx: Context):
    UserLocalDataSource, PrefsStorage(ctx, "ParrotPrefs") {

    override fun saveCredentials(access: String, refresh: String) {
        setString(KEY_ACCESS, access)
        setString(KEY_REFRESH, refresh)
    }

    override fun getAccess() = getString(KEY_ACCESS)

    override fun getRefresh() = getString(KEY_REFRESH)

    override fun saveStore(uuid: String, name: String) {
        setString(KEY_STORE_UUID, uuid)
        setString(KEY_STORE_NAME, name)
    }

    override fun getStoreUuid() = getString(KEY_STORE_UUID)

    override fun getStoreName() = getString(KEY_STORE_NAME)

    override fun isAuth() = getString(KEY_ACCESS).isNotBlank()

    private companion object {
        const val KEY_ACCESS = "key_access"
        const val KEY_REFRESH = "key_refresh"
        const val KEY_STORE_UUID = "key_store_uuid"
        const val KEY_STORE_NAME = "key_store_name"
    }
}
