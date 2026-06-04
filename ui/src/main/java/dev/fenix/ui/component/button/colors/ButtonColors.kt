package dev.fenix.ui.component.button.colors

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.core.type.Emphasis
import dev.fenix.ui.component.button.type.Variant

@Composable
private fun Emphasis.toContainerColor() = when (this) {
    Emphasis.Primary -> ConnectTheme.colors.primary
    Emphasis.Secondary -> ConnectTheme.colors.secondary
}

@Composable
private fun Emphasis.toContentColor() = when (this) {
    Emphasis.Primary -> ConnectTheme.colors.onPrimary
    Emphasis.Secondary -> ConnectTheme.colors.onSecondary
}

@Composable
fun buildButtonColors(
    variant: Variant,
    emphasis: Emphasis,
): ButtonColors {
    return when (variant) {
        Variant.Filled -> ButtonDefaults.buttonColors(
            containerColor = emphasis.toContainerColor(),
            contentColor = emphasis.toContentColor()
        )
        Variant.Outlined -> ButtonDefaults.outlinedButtonColors(
            contentColor = emphasis.toContainerColor()
        )
        Variant.Ghost -> ButtonDefaults.textButtonColors(
            contentColor = emphasis.toContainerColor()
        )
    }
}

@Composable
fun buildIconButtonColors(
    variant: Variant,
    emphasis: Emphasis,
): IconButtonColors {
    return when (variant) {
        Variant.Filled -> IconButtonDefaults.filledIconButtonColors(
            containerColor = emphasis.toContainerColor(),
            contentColor = emphasis.toContentColor()
        )
        Variant.Outlined -> IconButtonDefaults.outlinedIconButtonColors(
            contentColor = emphasis.toContainerColor(),
        )
        Variant.Ghost -> IconButtonDefaults.iconButtonColors(
            contentColor = emphasis.toContainerColor()
        )
    }
}

