package com.asn.popularmovies.data.api.retrofit

import com.asn.popularmovies.data.model.configuration.Configuration
import com.asn.popularmovies.data.model.popularMovies.PopularMovies
import com.asn.popularmovies.utils.EN_US_LOCALE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = EN_US_LOCALE
    ): Response<PopularMovies>

    @GET("configuration")
    suspend fun getConfiguration(
        @Query("api_key") apiKey: String,
    ): Response<Configuration>
}