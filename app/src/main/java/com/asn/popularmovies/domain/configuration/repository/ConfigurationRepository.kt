package com.asn.popularmovies.domain.configuration.repository

import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.configuration.Configuration
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    fun getConfiguration(): Flow<CustomResult<Configuration>>
}