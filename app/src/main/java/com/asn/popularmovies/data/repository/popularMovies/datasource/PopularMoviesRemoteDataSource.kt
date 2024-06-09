package com.asn.popularmovies.data.repository.popularMovies.datasource

import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.popularMovies.Movie

interface PopularMoviesRemoteDataSource {
    suspend fun getPopularMovies(page: Int): CustomResult<List<Movie>>
}