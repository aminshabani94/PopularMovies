package com.asn.popularmovies.di

import com.asn.popularmovies.data.api.retrofit.MainService
import com.asn.popularmovies.data.repository.configuration.ConfigurationRepositoryImpl
import com.asn.popularmovies.data.repository.configuration.datasource.ConfigurationRemoteDataSource
import com.asn.popularmovies.data.repository.configuration.datasourceImpl.ConfigurationRemoteDataSourceImpl
import com.asn.popularmovies.domain.configuration.repository.ConfigurationRepository
import com.asn.popularmovies.domain.configuration.usecase.GetConfigurationUseCase
import com.asn.popularmovies.utils.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigurationModule {
    @Singleton
    @Provides
    fun provideConfigurationRemoteDataSource(
        mainService: MainService,
        networkChecker: NetworkChecker
    ): ConfigurationRemoteDataSource =
        ConfigurationRemoteDataSourceImpl(
            mainService = mainService,
            networkChecker = networkChecker
        )

    @Singleton
    @Provides
    fun provideConfigurationRepository(
        remoteDataSource: ConfigurationRemoteDataSource
    ): ConfigurationRepository =
        ConfigurationRepositoryImpl(
            remoteDataSource = remoteDataSource
        )

    @Singleton
    @Provides
    fun provideGetConfigurationUseCase(
        configurationRepository: ConfigurationRepository
    ): GetConfigurationUseCase =
        GetConfigurationUseCase(configurationRepository)
}