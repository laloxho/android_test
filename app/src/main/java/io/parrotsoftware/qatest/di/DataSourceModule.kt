package io.parrotsoftware.qatest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}
