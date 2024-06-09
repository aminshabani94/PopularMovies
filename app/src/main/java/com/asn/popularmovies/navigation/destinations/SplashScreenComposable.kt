package com.asn.popularmovies.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.asn.popularmovies.ui.screens.splash.SplashScreen
import com.asn.popularmovies.ui.viewmodels.SharedViewModel
import com.asn.popularmovies.utils.SPLASH_SCREEN_ROUTE

fun NavGraphBuilder.splashScreenComposable(
    navigateToPopularMoviesScreen: () -> Unit,
    navigateToNetworkErrorScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(route = SPLASH_SCREEN_ROUTE) {
        SplashScreen(
            navigateToPopularMoviesScreen = navigateToPopularMoviesScreen,
            navigateToNetworkErrorScreen = navigateToNetworkErrorScreen,
            sharedViewModel = sharedViewModel
        )
    }
}