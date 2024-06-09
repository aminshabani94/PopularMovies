package com.asn.popularmovies.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.asn.popularmovies.ui.viewmodels.SharedViewModel
import com.asn.popularmovies.utils.SPLASH_SCREEN_ROUTE
import com.asn.popularmovies.navigation.destinations.networkErrorComposable
import com.asn.popularmovies.navigation.destinations.splashScreenComposable
import com.asn.popularmovies.navigation.destinations.popularMoviesComposable

@ExperimentalMaterial3Api
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN_ROUTE
    ) {
        splashScreenComposable(
            navigateToPopularMoviesScreen = screen.popularMovies,
            navigateToNetworkErrorScreen = screen.networkErrorScreen,
            sharedViewModel = sharedViewModel
        )
        popularMoviesComposable(
            navigateToMovieDetailsScreen = screen.movieDetails,
            sharedViewModel = sharedViewModel
        )
        networkErrorComposable(
            navigateToPopularMoviesScreen = screen.popularMovies,
            sharedViewModel = sharedViewModel
        )
    }
}