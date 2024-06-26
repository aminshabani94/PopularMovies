package com.asn.popularmovies.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.asn.popularmovies.R

private val DarkColorScheme = darkColorScheme(
    primary = DarkBackground,
    secondary = Blue,
    tertiary = Orange,
    background = DarkBackground,
    onSurface = BlueGray,
    onBackground = White
)

private val LightColorScheme = lightColorScheme(
    primary = LightBackground,
    secondary = Blue,
    tertiary = Orange,
    background = LightBackground,
    onSurface = BlueGray,
    onBackground = Black

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PopularMoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context).copy(
                    background = DarkBackground,
                    onSurface = BlueGray,
                    onBackground = White
                )
            } else {
                dynamicLightColorScheme(context).copy(
                    background = LightBackground,
                    onSurface = Black,
                    onBackground = Black
                )
            }
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor =
                if (darkTheme) DarkBackground.toArgb() else LightBackground.toArgb()
            window.setBackgroundDrawableResource(
                if (darkTheme) {
                    R.drawable.dark_theme_background
                } else {
                    R.drawable.light_theme_background
                }
            )
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}