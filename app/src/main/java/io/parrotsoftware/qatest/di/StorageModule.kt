package io.parrotsoftware.qatest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.parrotsoftware.qatest.data.datasource.local.database.AppDatabase
import io.parrotsoftware.qatest.data.datasource.local.preferences.PrefsStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDatabase.build(context)

    @Singleton
    @Provides
    fun provideProductsDao(appDatabase: AppDatabase) = appDatabase.productsDao()

    @Provides
    @Singleton
    fun providesPrefsStorage(@ApplicationContext context: Context) =
        PrefsStorage(context, "ParrotPrefs")
}
