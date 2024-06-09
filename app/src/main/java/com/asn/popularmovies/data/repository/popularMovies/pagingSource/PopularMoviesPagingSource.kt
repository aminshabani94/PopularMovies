package com.asn.popularmovies.data.repository.popularMovies.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.popularMovies.Movie
import com.asn.popularmovies.utils.NO_INTERNET_CONNECTION
import com.asn.popularmovies.utils.NO_POPULAR_MOVIES
import com.asn.popularmovies.data.repository.popularMovies.datasource.PopularMoviesRemoteDataSource
import kotlinx.coroutines.delay
import java.io.IOException
import javax.inject.Inject

class PopularMoviesPagingSource @Inject constructor(
    private val remoteDataSource: PopularMoviesRemoteDataSource
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = remoteDataSource.getPopularMovies(
                page = page
            )

            // Simulate loading delay
            delay(LOAD_DELAY_MILLIS)

            when (response) {
                is CustomResult.Success -> {
                    val popularMovies = response.data
                    LoadResult.Page(
                        data = popularMovies,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (popularMovies.isEmpty()) null else page + 1
                    )
                }

                is CustomResult.Failure -> LoadResult.Error(IOException(response.error.message))

                CustomResult.NoInternet -> LoadResult.Error(IOException(NO_INTERNET_CONNECTION))

                CustomResult.NullOrEmpty -> LoadResult.Error(IOException(NO_POPULAR_MOVIES))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val LOAD_DELAY_MILLIS = 1000L
    }
}