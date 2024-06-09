package com.asn.popularmovies.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.asn.popularmovies.R
import com.asn.popularmovies.ui.theme.DpDimensions.DP_16
import com.asn.popularmovies.ui.theme.SpDimensions.SP_18

@ExperimentalMaterial3Api
@Composable
fun TopAppBarWidget(title: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = SP_18,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        actions = {
            IconButton(
                modifier = Modifier.padding(end = DP_16),
                onClick = {}
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_movie),
                    contentDescription = stringResource(id = R.string.app_icon),
                )
            }
        }
    )
}