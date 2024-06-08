package com.asn.popularmovies.di

import com.asn.popularmovies.data.api.retrofit.MainService
import com.asn.popularmovies.data.repository.popularMovies.PopularMoviesRepositoryImpl
import com.asn.popularmovies.data.repository.popularMovies.datasource.PopularMoviesRemoteDataSource
import com.asn.popularmovies.data.repository.popularMovies.datasourceImpl.PopularMoviesRemoteDataSourceImpl
import com.asn.popularmovies.data.repository.popularMovies.pagingSource.PopularMoviesPagingSource
import com.asn.popularmovies.domain.popularMovies.repository.PopularMoviesRepository
import com.asn.popularmovies.domain.popularMovies.usecase.GetPopularMoviesUseCase
import com.asn.popularmovies.utils.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PopularMoviesModule {
    @Singleton
    @Provides
    fun providePopularMoviesRemoteDataSource(
        mainService: MainService,
        networkChecker: NetworkChecker
    ): PopularMoviesRemoteDataSource =
        PopularMoviesRemoteDataSourceImpl(
            mainService = mainService,
            networkChecker = networkChecker
        )

    @Singleton
    @Provides
    fun providePopularMoviesRepository(
        remoteDataSource: PopularMoviesPagingSource
    ): PopularMoviesRepository =
        PopularMoviesRepositoryImpl(remoteDataSource)

    @Singleton
    @Provides
    fun provideGetPopularMoviesUseCase(
        upcomingMoviesRepository: PopularMoviesRepository
    ): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(upcomingMoviesRepository)
}