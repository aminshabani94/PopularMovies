package com.asn.popularmovies.domain.configuration.usecase

import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.configuration.Configuration
import com.asn.popularmovies.domain.configuration.repository.ConfigurationRepository
import kotlinx.coroutines.flow.Flow

class GetConfigurationUseCase(
    private val configurationRepository: ConfigurationRepository
) {
    operator fun invoke(): Flow<CustomResult<Configuration>> =
        configurationRepository.getConfiguration()
}