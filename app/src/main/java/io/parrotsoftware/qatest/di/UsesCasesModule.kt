package io.parrotsoftware.qatest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import io.parrotsoftware.qatest.domain.repositories.UserRepository
import io.parrotsoftware.qatest.domain.usescases.GetCredentialsUseCase
import io.parrotsoftware.qatest.domain.usescases.GetProductsUseCase
import io.parrotsoftware.qatest.domain.usescases.GetStoreUseCase
import io.parrotsoftware.qatest.domain.usescases.LogoutUseCase
import io.parrotsoftware.qatest.domain.usescases.LoginUseCase
import io.parrotsoftware.qatest.domain.usescases.SetProductsStateUseCase
import io.parrotsoftware.qatest.domain.usescases.UserExistsUseCase

@Module
@InstallIn(SingletonComponent::class)
class UsesCasesModule {

    @Provides
    fun providesLoginUseCase(userRepository: UserRepository) = LoginUseCase(userRepository)

    @Provides
    fun providesLogoutUseCase(userRepository: UserRepository) = LogoutUseCase(userRepository)

    @Provides
    fun providesUserExistsUseCase(userRepository: UserRepository) =
        UserExistsUseCase(userRepository)

    @Provides
    fun providesGetCredentialsUseCase(userRepository: UserRepository) =
        GetCredentialsUseCase(userRepository)

    @Provides
    fun providesGetStoreUseCase(userRepository: UserRepository) = GetStoreUseCase(userRepository)

    @Provides
    fun providesGetProductsUseCase(productRepository: ProductRepository) =
        GetProductsUseCase(productRepository)

    @Provides
    fun providesSetProductsStateUseCase(productRepository: ProductRepository) =
        SetProductsStateUseCase(productRepository)
}
