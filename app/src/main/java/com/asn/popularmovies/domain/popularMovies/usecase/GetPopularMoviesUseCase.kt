package com.asn.popularmovies.domain.popularMovies.usecase

import androidx.paging.PagingData
import com.asn.popularmovies.data.model.popularMovies.Movie
import com.asn.popularmovies.domain.popularMovies.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(private val popularMoviesRepository: PopularMoviesRepository) {
    operator fun invoke(): Flow<PagingData<Movie>> =
        popularMoviesRepository.getPopularMovies()
}