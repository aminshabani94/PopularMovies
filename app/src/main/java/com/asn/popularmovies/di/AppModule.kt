package com.asn.popularmovies.di

import android.content.Context
import com.asn.popularmovies.MainApplication
import com.asn.popularmovies.utils.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context as MainApplication
    }

    @Singleton
    @Provides
    fun provideNetworkChecker(@ApplicationContext context: Context): NetworkChecker {
        return NetworkChecker(context)
    }
}