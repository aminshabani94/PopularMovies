package com.asn.popularmovies.data.repository.popularMovies.datasourceImpl

import com.asn.popularmovies.BuildConfig
import com.asn.popularmovies.data.api.retrofit.MainService
import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.popularMovies.Movie
import com.asn.popularmovies.utils.NetworkChecker
import com.asn.popularmovies.data.repository.popularMovies.datasource.PopularMoviesRemoteDataSource
import javax.inject.Inject

class PopularMoviesRemoteDataSourceImpl @Inject constructor(
    private val mainService: MainService,
    private val networkChecker: NetworkChecker
) : PopularMoviesRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): CustomResult<List<Movie>> =
        try {
            val hasNetwork = networkChecker.checkNetwork()
            if (!hasNetwork) {
                CustomResult.NoInternet
            } else {
                val response = mainService.getPopularMovies(
                    page = page,
                    apiKey = BuildConfig.API_KEY
                )
                if (response.isSuccessful) {
                    val popularMovies = response.body()
                    if (popularMovies != null) {
                        if (popularMovies.results.isNotEmpty()) {
                            CustomResult.Success(popularMovies.results)
                        } else {
                            CustomResult.NullOrEmpty
                        }
                    } else {
                        CustomResult.NullOrEmpty
                    }
                } else {
                    CustomResult.Failure(Exception(response.errorBody().toString()))
                }
            }
        } catch (e: Exception) {
            CustomResult.Failure(e)
        }
}