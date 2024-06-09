package com.asn.popularmovies.data.repository.configuration.datasourceImpl

import com.asn.popularmovies.BuildConfig
import com.asn.popularmovies.data.api.retrofit.MainService
import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.configuration.Configuration
import com.asn.popularmovies.data.repository.configuration.datasource.ConfigurationRemoteDataSource
import com.asn.popularmovies.utils.NetworkChecker
import javax.inject.Inject

class ConfigurationRemoteDataSourceImpl @Inject constructor(
    private val mainService: MainService,
    private val networkChecker: NetworkChecker
) : ConfigurationRemoteDataSource {
    override suspend fun getConfiguration(): CustomResult<Configuration> =
        try {
            val hasNetwork = networkChecker.checkNetwork()
            if (!hasNetwork) {
                CustomResult.NoInternet
            } else {
                val response = mainService.getConfiguration(
                    apiKey = BuildConfig.API_KEY
                )
                if (response.isSuccessful) {
                    val configuration = response.body()
                    if (configuration != null) {
                        CustomResult.Success(configuration)
                    } else {
                        CustomResult.NullOrEmpty
                    }
                } else {
                    CustomResult.Failure(Exception(response.errorBody().toString()))
                }
            }
        } catch (e: Exception) {
            CustomResult.Failure(e)
        }
}