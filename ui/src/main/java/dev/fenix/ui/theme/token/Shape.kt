package dev.fenix.ui.theme.token

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

private val CornerRadius = Measurement(
    xs = 4.dp,
    small = 8.dp,
    medium = 16.dp,
    large = 24.dp,
    xl = 32.dp,
)

val shapes = Shapes(
    extraSmall = RoundedCornerShape(CornerRadius.xs),
    small = RoundedCornerShape(CornerRadius.small),
    medium = RoundedCornerShape(CornerRadius.medium),
    large = RoundedCornerShape(CornerRadius.large),
    extraLarge = RoundedCornerShape(CornerRadius.xl),
)