package io.parrotsoftware.qatest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.parrotsoftware.qatest.data.datasource.local.ProductLocalDataSource
import io.parrotsoftware.qatest.data.datasource.local.UserLocalDataSource
import io.parrotsoftware.qatest.data.datasource.local.impl.ProductLocalDataSourceImpl
import io.parrotsoftware.qatest.data.datasource.local.impl.UserLocalDataSourceImpl
import io.parrotsoftware.qatest.data.datasource.remote.ProductRemoteDataSource
import io.parrotsoftware.qatest.data.datasource.remote.UserRemoteDataSource
import io.parrotsoftware.qatest.data.datasource.remote.impl.ProductRemoteDataSourceImpl
import io.parrotsoftware.qatest.data.datasource.remote.impl.UserRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideProductRemoteDataSource(remoteDataSource: ProductRemoteDataSourceImpl): ProductRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideUserRemoteDataSource(remoteDataSource: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideUserLocalDataSource(localDataSource: UserLocalDataSourceImpl): UserLocalDataSource

    @Singleton
    @Binds
    abstract fun provideProductLocalDataSource(localDataSource: ProductLocalDataSourceImpl): ProductLocalDataSource
}
