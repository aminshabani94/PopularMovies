package com.asn.popularmovies.ui.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.asn.popularmovies.R
import com.asn.popularmovies.ui.theme.DpDimensions.DP_16
import com.asn.popularmovies.ui.theme.DpDimensions.DP_36
import com.asn.popularmovies.ui.theme.DpDimensions.ERROR_ITEM_VERTICAL_PADDING
import com.asn.popularmovies.ui.theme.DpDimensions.TRY_AGAIN_BUTTON_CORNER_RADIUS
import com.asn.popularmovies.ui.theme.DpDimensions.TRY_AGAIN_BUTTON_STROKE_WIDTH
import com.asn.popularmovies.ui.theme.ErrorRed
import com.asn.popularmovies.ui.theme.SpDimensions.SP_14
import com.asn.popularmovies.ui.theme.SpDimensions.SP_16

@Composable
fun ErrorItem(
    errorMessage: String,
    onClick: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DP_16, vertical = ERROR_ITEM_VERTICAL_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = errorMessage,
                fontSize = SP_16,
                color = MaterialTheme.colorScheme.onBackground
            )
            Button(
                modifier = Modifier
                    .border(
                        width = TRY_AGAIN_BUTTON_STROKE_WIDTH,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(TRY_AGAIN_BUTTON_CORNER_RADIUS)
                    )
                    .height(DP_36),
                onClick = {
                    onClick()
                },
                shape = RoundedCornerShape(
                    TRY_AGAIN_BUTTON_CORNER_RADIUS
                ),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            ) {
                Text(
                    text = stringResource(id = R.string.try_again),
                    fontSize = SP_14,
                    color = ErrorRed
                )
            }
        }
    }
}

@Preview
@Composable
fun ErrorItemPreview() {
    ErrorItem(errorMessage = stringResource(id = R.string.something_went_wrong)) {}
}