package com.asn.popularmovies.ui.viewmodels

import androidx.paging.PagingData
import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.configuration.Configuration
import com.asn.popularmovies.data.model.popularMovies.Movie
import com.asn.popularmovies.domain.configuration.usecase.GetConfigurationUseCase
import com.asn.popularmovies.domain.popularMovies.usecase.GetPopularMoviesUseCase
import com.asn.popularmovies.utils.TestCoroutineRule
import com.asn.popularmovies.utils.collectData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SharedViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @RelaxedMockK
    private lateinit var getConfigurationUseCase: GetConfigurationUseCase

    @MockK
    private lateinit var movie: Movie

    @MockK
    private lateinit var configuration: Configuration

    private lateinit var sharedViewModel: SharedViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sharedViewModel = SharedViewModel(getPopularMoviesUseCase, getConfigurationUseCase)// Set up the behavior for GetConfigurationUseCase().invoke()
        coEvery { getConfigurationUseCase.invoke() } returns flowOf(CustomResult.Success(configuration))
    }

    @Test
    fun `getPopularMovies returns success when API call is successful`() = testCoroutineRule.runTest {
        val mockMovies = PagingData.from(listOf(movie))
        coEvery { getPopularMoviesUseCase.invoke() } returns flowOf(mockMovies)

        sharedViewModel.getPopularMovies()

        val actualMoviesPagingData = sharedViewModel.popularMoviesState.value
        val actualMovies = actualMoviesPagingData.collectData()
        val expectedMovies = mockMovies.collectData()
        Assert.assertEquals(expectedMovies, actualMovies)
    }

    @Test
    fun `getConfiguration returns success when API call is successful`() = testCoroutineRule.runTest {
        val mockResponse = CustomResult.Success(configuration)
        coEvery { getConfigurationUseCase.invoke() } returns flowOf(mockResponse)

        sharedViewModel.getConfiguration()

        Assert.assertEquals(sharedViewModel.configuration, configuration)
    }
}