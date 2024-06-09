package com.asn.popularmovies.navigation

import androidx.navigation.NavHostController
import com.asn.popularmovies.utils.NETWORK_ERROR_SCREEN_ROUTE
import com.asn.popularmovies.utils.POPULAR_MOVIES_ROUTE
import com.asn.popularmovies.utils.SPLASH_SCREEN_ROUTE

class Screens(navController: NavHostController) {
    val splashScreen: () -> Unit = {
        navController.navigate(route = SPLASH_SCREEN_ROUTE)
    }

    val networkErrorScreen: () -> Unit = {
        navController.navigate(route = NETWORK_ERROR_SCREEN_ROUTE) {
            popUpTo(NETWORK_ERROR_SCREEN_ROUTE) {
                inclusive = true
            }
        }
    }

    val popularMovies: () -> Unit = {
        navController.navigate(route = POPULAR_MOVIES_ROUTE) {
            popUpTo(POPULAR_MOVIES_ROUTE) {
                inclusive = true
            }
        }
    }

    val movieDetails: (Int) -> Unit = { movieId ->
        navController.navigate(route = "movieDetails/$movieId")
    }
}