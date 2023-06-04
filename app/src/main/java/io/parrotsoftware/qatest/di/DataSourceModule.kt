package io.parrotsoftware.qatest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.parrotsoftware.qatest.data.datasource.local.LocalDataSource
import io.parrotsoftware.qatest.data.datasource.local.impl.LocalDataSourceImpl
import io.parrotsoftware.qatest.data.datasource.local.preferences.PrefsStorage
import io.parrotsoftware.qatest.data.datasource.local.preferences.PrefsStorageImpl
import io.parrotsoftware.qatest.data.datasource.remote.RemoteDataSource
import io.parrotsoftware.qatest.data.datasource.remote.impl.RemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Singleton
    @Binds
    abstract fun providePrefsStorage(localDataSource: PrefsStorageImpl): PrefsStorage
}
