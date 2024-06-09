package com.asn.popularmovies.navigation.destinations

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.asn.popularmovies.ui.screens.networkError.NetworkErrorScreen
import com.asn.popularmovies.ui.viewmodels.SharedViewModel
import com.asn.popularmovies.utils.NETWORK_ERROR_SCREEN_ROUTE

@ExperimentalMaterial3Api
fun NavGraphBuilder.networkErrorComposable(
    navigateToPopularMoviesScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(route = NETWORK_ERROR_SCREEN_ROUTE) {
        NetworkErrorScreen(
            navigateToPopularMoviesScreen = navigateToPopularMoviesScreen,
            sharedViewModel = sharedViewModel
        )
    }
}