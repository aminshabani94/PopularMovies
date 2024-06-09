package com.asn.popularmovies.ui.screens.networkError

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.asn.popularmovies.R
import com.asn.popularmovies.ui.theme.Blue
import com.asn.popularmovies.ui.theme.DpDimensions.CORNER_RADIUS
import com.asn.popularmovies.ui.theme.DpDimensions.DP_0
import com.asn.popularmovies.ui.theme.DpDimensions.DP_12
import com.asn.popularmovies.ui.theme.DpDimensions.DP_16
import com.asn.popularmovies.ui.theme.DpDimensions.DP_24
import com.asn.popularmovies.ui.theme.DpDimensions.DP_4
import com.asn.popularmovies.ui.theme.DpDimensions.DP_40
import com.asn.popularmovies.ui.theme.DpDimensions.DP_92
import com.asn.popularmovies.ui.theme.SpDimensions.SP_14
import com.asn.popularmovies.ui.theme.SpDimensions.SP_16
import com.asn.popularmovies.ui.theme.SpDimensions.SP_28
import com.asn.popularmovies.ui.theme.White
import com.asn.popularmovies.ui.viewmodels.NavigationState
import com.asn.popularmovies.ui.viewmodels.SharedViewModel
import com.asn.popularmovies.ui.widgets.LoadingItem

@Composable
fun NetworkErrorScreen(
    navigateToPopularMoviesScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {

    val navigationState = sharedViewModel.navigationState.collectAsState()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.padding(horizontal = DP_16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = DP_92, bottom = DP_12),
                    fontSize = SP_28,
                    text = stringResource(id = R.string.error_title),
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = DP_4, bottom = DP_12),
                    fontSize = SP_16,
                    text = stringResource(id = R.string.error_message),
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = DP_12)
                        .height(DP_40),
                    onClick = {
                        sharedViewModel.getConfiguration()
                    },
                    shape = RoundedCornerShape(CORNER_RADIUS),
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = Blue
                    )
                ) {
                    when (navigationState.value) {
                        is NavigationState.Loading -> {
                            LoadingItem(
                                color = White,
                                padding = DP_0,
                                size = DP_24
                            )
                        }

                        is NavigationState.NavigateToErrorScreen -> {
                            Text(
                                text = stringResource(id = R.string.retry),
                                fontSize = SP_14,
                                color = White
                            )
                        }

                        NavigationState.NavigateToPopularMovies -> {
                            navigateToPopularMoviesScreen()
                        }
                    }
                }
            }
        }
    }
}