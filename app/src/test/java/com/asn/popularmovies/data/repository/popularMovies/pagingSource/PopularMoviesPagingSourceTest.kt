package com.asn.popularmovies.data.repository.popularMovies.pagingSource

import androidx.paging.PagingSource
import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.popularMovies.Movie
import com.asn.popularmovies.data.repository.popularMovies.datasource.PopularMoviesRemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PopularMoviesPagingSourceTest {

    @MockK
    private lateinit var remoteDataSource: PopularMoviesRemoteDataSource

    @MockK
    private lateinit var movie: Movie

    private lateinit var popularMoviesPagingSource: PopularMoviesPagingSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        popularMoviesPagingSource = PopularMoviesPagingSource(remoteDataSource)
    }

    @Test
    fun `load returns success when API call is successful`() = runTest {
        val mockMovies = listOf(movie)
        val mockResponse = CustomResult.Success(mockMovies)
        coEvery { remoteDataSource.getPopularMovies(any()) } returns mockResponse

        val result = popularMoviesPagingSource.load(PagingSource.LoadParams.Refresh(0, 1, false))

        Assert.assertTrue(result is PagingSource.LoadResult.Page)
    }

    @Test
    fun `load returns Failure when API call is unsuccessful`() = runTest {
        val mockResponse = CustomResult.Failure(Exception("API call failed"))
        coEvery { remoteDataSource.getPopularMovies(any()) } returns mockResponse

        val result = popularMoviesPagingSource.load(PagingSource.LoadParams.Refresh(0, 1, false))

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun `load returns NoInternet when network is not available`() = runTest {
        coEvery { remoteDataSource.getPopularMovies(any()) } returns CustomResult.NoInternet

        val result = popularMoviesPagingSource.load(PagingSource.LoadParams.Refresh(0, 1, false))

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun `load returns NullOrEmpty when response is null or empty`() = runTest {
        coEvery { remoteDataSource.getPopularMovies(any()) } returns CustomResult.NullOrEmpty

        val result = popularMoviesPagingSource.load(PagingSource.LoadParams.Refresh(0, 1, false))

        assertTrue(result is PagingSource.LoadResult.Error)
    }
}