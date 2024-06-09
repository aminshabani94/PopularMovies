package com.asn.popularmovies.ui.screens.popularMovies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.asn.popularmovies.R
import com.asn.popularmovies.data.model.popularMovies.Movie
import com.asn.popularmovies.ui.theme.DpDimensions.CORNER_RADIUS
import com.asn.popularmovies.ui.theme.DpDimensions.DP_12
import com.asn.popularmovies.ui.theme.DpDimensions.MOVIE_IMAGE_MAX_HEIGHT
import com.asn.popularmovies.ui.theme.DpDimensions.MOVIE_IMAGE_MAX_WIDTH
import com.asn.popularmovies.ui.theme.SpDimensions.SP_12
import com.asn.popularmovies.ui.theme.SpDimensions.SP_16
import com.asn.popularmovies.ui.viewmodels.SharedViewModel

@Composable
fun MovieItem(
    movie: Movie,
    sharedViewModel: SharedViewModel,
    onClick: (Movie) -> Unit
) {
    Surface(
        color = Color.Transparent,
        shape = RoundedCornerShape(CORNER_RADIUS),
        onClick = {
            onClick(movie)
        }
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = MOVIE_IMAGE_MAX_WIDTH),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(CORNER_RADIUS))
                    .widthIn(max = MOVIE_IMAGE_MAX_WIDTH)
                    .heightIn(max = MOVIE_IMAGE_MAX_HEIGHT),
                model = movie.getFullPosterPath(sharedViewModel.configuration),
                contentDescription = stringResource(id = R.string.movie_image),
                contentScale = ContentScale.Fit,
            )
            Text(
                modifier = Modifier
                    .padding(top = DP_12),
                fontSize = SP_16,
                text = movie.title,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                fontSize = SP_12,
                text = movie.overview,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}