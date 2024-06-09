package com.asn.popularmovies.data.repository.configuration.datasourceImpl

import com.asn.popularmovies.data.api.retrofit.MainService
import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.configuration.Configuration
import com.asn.popularmovies.utils.EMPTY_STRING
import com.asn.popularmovies.utils.NetworkChecker
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ConfigurationRemoteDataSourceImplTest {

    @MockK
    private lateinit var mainService: MainService

    @MockK
    private lateinit var networkChecker:NetworkChecker

    @MockK
    private lateinit var configuration: Configuration

    private lateinit var dataSource: ConfigurationRemoteDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = ConfigurationRemoteDataSourceImpl(mainService, networkChecker)
    }

    @Test
    fun `getConfiguration returns success when network is available and API call is successful`() = runTest {
        val mockResponse = Response.success(configuration)
        coEvery { networkChecker.checkNetwork() } returns true
        coEvery { mainService.getConfiguration(any()) } returns mockResponse

        val result = dataSource.getConfiguration()

        assertTrue(result is CustomResult.Success)
        assertEquals(configuration, (result as CustomResult.Success).data)
    }

    @Test
    fun `getConfiguration returns NoInternet when network is not available`() = runTest {
        coEvery { networkChecker.checkNetwork() } returns false

        val result = dataSource.getConfiguration()

        assertTrue(result is CustomResult.NoInternet)
    }

    @Test
    fun `getConfiguration returns Failure when API call is unsuccessful`() = runTest {
        val mockResponse = Response.error<Configuration>(500, EMPTY_STRING.toResponseBody())
        coEvery { networkChecker.checkNetwork() } returns true
        coEvery { mainService.getConfiguration(any()) } returns mockResponse

        val result = dataSource.getConfiguration()

        assertTrue(result is CustomResult.Failure)
    }

    @Test
    fun `getConfiguration returns NullOrEmpty when response is null or empty`() = runTest {
        val mockResponse = Response.success(null as Configuration?)
        coEvery { networkChecker.checkNetwork() } returns true
        coEvery { mainService.getConfiguration(any()) } returns mockResponse

        val result = dataSource.getConfiguration()

        assertTrue(result is CustomResult.NullOrEmpty)
    }
}