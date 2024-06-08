package com.asn.popularmovies.data.repository.configuration

import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.configuration.Configuration
import com.asn.popularmovies.data.repository.configuration.datasource.ConfigurationRemoteDataSource
import com.asn.popularmovies.domain.configuration.repository.ConfigurationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConfigurationRepositoryImpl @Inject constructor(
    private val remoteDataSource: ConfigurationRemoteDataSource,
) : ConfigurationRepository {
    override fun getConfiguration(): Flow<CustomResult<Configuration>> = flow {
        val result = remoteDataSource.getConfiguration()
        emit(result)
    }
}