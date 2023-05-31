package io.parrotsoftware.qatest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.parrotsoftware.qatest.data.repositories.ProductRepositoryImpl
import io.parrotsoftware.qatest.data.repositories.UserRepositoryImpl
import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import io.parrotsoftware.qatest.domain.repositories.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideProductRepository(repository: ProductRepositoryImpl): ProductRepository

    @Singleton
    @Binds
    abstract fun provideUserRepository(repository: UserRepositoryImpl): UserRepository
}
