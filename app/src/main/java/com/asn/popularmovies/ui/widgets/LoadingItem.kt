package com.asn.popularmovies.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.asn.popularmovies.ui.theme.Blue
import com.asn.popularmovies.ui.theme.DpDimensions.DP_32
import com.asn.popularmovies.ui.theme.DpDimensions.DP_50

@Composable
fun LoadingItem(
    color: Color = Blue,
    padding: Dp = DP_50,
    size: Dp = DP_32
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(padding)
                .size(size),
            color = color
        )
    }
}