package com.asn.popularmovies.data.repository.configuration

import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.configuration.Configuration
import com.asn.popularmovies.data.repository.configuration.datasource.ConfigurationRemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ConfigurationRepositoryImplTest {

    @MockK
    private lateinit var remoteDataSource: ConfigurationRemoteDataSource

    @MockK
    private lateinit var configuration: Configuration

    private lateinit var configurationRepositoryImpl: ConfigurationRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        configurationRepositoryImpl = ConfigurationRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `getConfiguration returns success when API call is successful`() = runTest {
        val mockResult = CustomResult.Success(configuration)
        coEvery { remoteDataSource.getConfiguration() } returns mockResult

        val result = configurationRepositoryImpl.getConfiguration().first()

        assertTrue(result is CustomResult.Success)
        assertEquals(configuration, (result as CustomResult.Success).data)
    }

    @Test
    fun `getConfiguration returns Failure when API call is unsuccessful`() = runTest {
        val mockResult = CustomResult.Failure(Exception("Error"))
        coEvery { remoteDataSource.getConfiguration() } returns mockResult

        val result = configurationRepositoryImpl.getConfiguration().first()

        assertTrue(result is CustomResult.Failure)
        assertEquals("Error", (result as CustomResult.Failure).error.message)
    }

    @Test
    fun `getConfiguration returns NullOrEmpty when response is null or empty`() = runTest {
        val mockResult = CustomResult.NullOrEmpty
        coEvery { remoteDataSource.getConfiguration() } returns mockResult

        val result = configurationRepositoryImpl.getConfiguration().first()

        assertTrue(result is CustomResult.NullOrEmpty)
    }
}