package com.asn.popularmovies.data.repository.popularMovies.datasourceImpl

import com.asn.popularmovies.data.api.retrofit.MainService
import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.popularMovies.Movie
import com.asn.popularmovies.data.model.popularMovies.PopularMovies
import com.asn.popularmovies.utils.EMPTY_STRING
import com.asn.popularmovies.utils.NetworkChecker
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PopularMoviesRemoteDataSourceImplTest {

    @MockK
    private lateinit var mainService: MainService

    @MockK
    private lateinit var networkChecker: NetworkChecker

    @MockK
    private lateinit var popularMovies: PopularMovies

    @MockK
    private lateinit var movie: Movie

    private lateinit var popularMoviesRemoteDataSourceImpl: PopularMoviesRemoteDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        popularMoviesRemoteDataSourceImpl =
            PopularMoviesRemoteDataSourceImpl(mainService, networkChecker)
    }

    @Test
    fun `getPopularMovies returns success when network is available and API call is successful`() =
        runTest {
            every { popularMovies.results } returns listOf(movie)
            val mockResponse = Response.success(popularMovies)
            coEvery { networkChecker.checkNetwork() } returns true
            coEvery { mainService.getPopularMovies(any(), any()) } returns mockResponse

            val result = popularMoviesRemoteDataSourceImpl.getPopularMovies(1)

            Assert.assertTrue(result is CustomResult.Success)
            Assert.assertEquals(listOf(movie), (result as CustomResult.Success).data)
        }

    @Test
    fun `getPopularMovies returns NoInternet when network is not available`() = runTest {
        coEvery { networkChecker.checkNetwork() } returns false

        val result = popularMoviesRemoteDataSourceImpl.getPopularMovies(1)

        assertTrue(result is CustomResult.NoInternet)
    }

    @Test
    fun `getPopularMovies returns Failure when API call is unsuccessful`() = runTest {
        val mockResponse = Response.error<PopularMovies>(500, EMPTY_STRING.toResponseBody())
        coEvery { networkChecker.checkNetwork() } returns true
        coEvery { mainService.getPopularMovies(any(), any()) } returns mockResponse

        val result = popularMoviesRemoteDataSourceImpl.getPopularMovies(1)

        assertTrue(result is CustomResult.Failure)
    }

    @Test
    fun `getPopularMovies returns NullOrEmpty when response is null or empty`() = runTest {
        val mockResponse = Response.success(null as PopularMovies?)
        coEvery { networkChecker.checkNetwork() } returns true
        coEvery { mainService.getPopularMovies(any(), any()) } returns mockResponse

        val result = popularMoviesRemoteDataSourceImpl.getPopularMovies(1)

        assertTrue(result is CustomResult.NullOrEmpty)
    }
}