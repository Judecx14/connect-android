package dev.fenix.ui.theme.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import dev.fenix.ui.theme.token.dimension.DimensionSuite
import dev.fenix.ui.theme.token.dimension.Dimensions


val LocalDimen = staticCompositionLocalOf { Dimensions.compact }

@Composable
fun ProvideDimension(suite: DimensionSuite, content: @Composable () -> Unit) {
    val instance = remember { suite }

    CompositionLocalProvider(LocalDimen provides instance) { content() }
}

