package io.parrotsoftware.qatest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.parrotsoftware.qa_network.interactors.NetworkInteractor
import io.parrotsoftware.qa_network.interactors.impl.NetworkInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun provideNetworkInteractor(interactor: NetworkInteractorImpl): NetworkInteractor
}
