package com.asn.popularmovies.ui.screens.popularMovies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.asn.popularmovies.R
import com.asn.popularmovies.ui.viewmodels.SharedViewModel
import com.asn.popularmovies.ui.widgets.TopAppBarWidget


@ExperimentalMaterial3Api
@Composable
fun PopularMoviesScreen(
    navigateToMovieDetailsScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            TopAppBarWidget(title = stringResource(id = R.string.app_bar_title))
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            MovieList(
                sharedViewModel = sharedViewModel,
                scope = scope,
                snackBarHostState = snackBarHostState
            )
        }
    }
}