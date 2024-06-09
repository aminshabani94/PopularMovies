package com.asn.popularmovies.ui.screens.popularMovies

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.asn.popularmovies.R
import com.asn.popularmovies.data.model.popularMovies.Movie
import com.asn.popularmovies.ui.theme.DpDimensions.DP_16
import com.asn.popularmovies.ui.viewmodels.SharedViewModel
import com.asn.popularmovies.ui.widgets.ErrorItem
import com.asn.popularmovies.ui.widgets.LoadingItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MovieList(
    sharedViewModel: SharedViewModel,
    scope: CoroutineScope,
    snackBarHostState: SnackbarHostState
) {

    val lazyColumnState = rememberLazyListState()
    val movies: LazyPagingItems<Movie> =
        sharedViewModel.popularMoviesState.collectAsLazyPagingItems()
    val configuration = LocalConfiguration.current
    val columns = when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> 3
        Configuration.ORIENTATION_LANDSCAPE -> 6
        else -> 3
    }

    LazyColumn(
        state = lazyColumnState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(movies.itemCount / columns) { rowIndex ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = DP_16,
                        start = DP_16,
                        end = DP_16
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                for (columnIndex in 0 until columns) {
                    val itemIndex = rowIndex * columns + columnIndex
                    if (itemIndex < movies.itemCount) {
                        movies[itemIndex]?.let {
                            MovieItem(
                                movie = it,
                                sharedViewModel = sharedViewModel,
                                onClick = { movie ->
                                    scope.launch {
                                        snackBarHostState.showSnackbar(message = movie.title)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        with(movies) {
            when (loadState.append) {
                is LoadState.Loading -> {
                    item {
                        LoadingItem()
                    }
                }

                is LoadState.Error -> {
                    item {
                        ErrorItem(
                            errorMessage = stringResource(id = R.string.something_went_wrong)
                        ) {
                            movies.retry()
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}