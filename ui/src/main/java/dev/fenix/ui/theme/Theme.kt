package dev.fenix.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import dev.fenix.ui.theme.provider.LocalDimen
import dev.fenix.ui.theme.provider.ProvideDimension
import dev.fenix.ui.theme.token.color_scheme.DarkColorScheme
import dev.fenix.ui.theme.token.color_scheme.LightColorScheme
import dev.fenix.ui.theme.token.dimension.DimensionSuite
import dev.fenix.ui.theme.token.dimension.Dimensions
import dev.fenix.ui.theme.token.dimension.suiteFor
import dev.fenix.ui.theme.token.font.Typography
import dev.fenix.ui.theme.token.shapes

@Composable
fun ConnectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val screenSize = LocalWindowInfo.current.containerSize

    val screenWidth: Int

    with(LocalDensity.current) {
        screenWidth = screenSize.width.toDp().value.toInt()
    }

    val suite = Dimensions.suiteFor(screenWidth)

    ProvideDimension(suite) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = shapes,
            content = content
        )
    }
}


object ConnectTheme {
    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val dimensions: DimensionSuite
        @Composable
        @ReadOnlyComposable
        get() = LocalDimen.current


    val typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}