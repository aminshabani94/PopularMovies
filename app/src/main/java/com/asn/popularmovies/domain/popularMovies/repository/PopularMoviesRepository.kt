package com.asn.popularmovies.domain.popularMovies.repository

import androidx.paging.PagingData
import com.asn.popularmovies.data.model.popularMovies.Movie
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
}