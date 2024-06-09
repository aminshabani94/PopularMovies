package com.asn.popularmovies.ui.screens.splash

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.asn.popularmovies.R
import com.asn.popularmovies.ui.theme.DpDimensions.DP_100
import com.asn.popularmovies.ui.viewmodels.NavigationState
import com.asn.popularmovies.ui.viewmodels.SharedViewModel
import com.asn.popularmovies.ui.widgets.LoadingItem
import com.asn.popularmovies.utils.toPx
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToPopularMoviesScreen: () -> Unit,
    navigateToNetworkErrorScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {

    val navigationState = sharedViewModel.navigationState.collectAsState()
    val isAnimating = remember { mutableStateOf(true) }
    val transition = updateTransition(isAnimating.value, label = "transition")
    val scale by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 500) },
        label = "scale"
    ) { if (it) 1f else 0.4f }
    val offsetX by transition.animateDp(
        transitionSpec = { tween(durationMillis = 500) },
        label = "offsetX"
    ) { if (it) 0.dp else (160).dp } // Negative value to move to the right
    val offsetY by transition.animateDp(
        transitionSpec = { tween(durationMillis = 500) },
        label = "offsetY"
    ) { if (it) 0.dp else (-310).dp }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_movie),
                    contentDescription = stringResource(id = R.string.app_icon),
                    modifier = Modifier
                        .size(DP_100)
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offsetX.toPx(),
                            translationY = offsetY.toPx()
                        )
                )
                LoadingItem()
            }
        }
    }

    when (navigationState.value) {
        is NavigationState.NavigateToPopularMovies -> {
            LaunchedEffect(Unit) {
                isAnimating.value = false
                delay(1000L)
                navigateToPopularMoviesScreen()
            }
        }

        is NavigationState.NavigateToErrorScreen -> {
            LaunchedEffect(Unit) {
                delay(1000L)
                navigateToNetworkErrorScreen()
            }
        }

        NavigationState.Loading -> Unit
    }
}