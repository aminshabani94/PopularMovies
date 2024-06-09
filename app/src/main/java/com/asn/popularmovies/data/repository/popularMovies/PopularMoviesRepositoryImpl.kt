package com.asn.popularmovies.data.repository.popularMovies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.asn.popularmovies.data.model.popularMovies.Movie
import com.asn.popularmovies.data.model.popularMovies.PopularMovies.Companion.PAGE_SIZE
import com.asn.popularmovies.domain.popularMovies.repository.PopularMoviesRepository
import com.asn.popularmovies.data.repository.popularMovies.pagingSource.PopularMoviesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: PopularMoviesPagingSource
) : PopularMoviesRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { remoteDataSource }
        ).flow
    }
}