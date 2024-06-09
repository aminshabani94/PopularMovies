package com.asn.popularmovies.data.repository.configuration.datasource

import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.configuration.Configuration

interface ConfigurationRemoteDataSource {
    suspend fun getConfiguration(): CustomResult<Configuration>
}