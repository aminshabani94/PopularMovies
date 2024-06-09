package com.asn.popularmovies.navigation.destinations

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.asn.popularmovies.ui.screens.popularMovies.PopularMoviesScreen
import com.asn.popularmovies.ui.viewmodels.SharedViewModel
import com.asn.popularmovies.utils.POPULAR_MOVIES_ROUTE

@ExperimentalMaterial3Api
fun NavGraphBuilder.popularMoviesComposable(
    navigateToMovieDetailsScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(route = POPULAR_MOVIES_ROUTE) {
        PopularMoviesScreen(
            navigateToMovieDetailsScreen = navigateToMovieDetailsScreen,
            sharedViewModel = sharedViewModel
        )
    }
}