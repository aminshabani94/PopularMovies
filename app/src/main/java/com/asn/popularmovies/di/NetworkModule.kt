package com.asn.popularmovies.di

import com.asn.popularmovies.BuildConfig
import com.asn.popularmovies.data.api.retrofit.MainService
import com.asn.popularmovies.data.api.retrofit.ServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideUpcomingMovieService(): MainService =
        ServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_API_URL)

}