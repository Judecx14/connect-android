package dev.fenix.ui.theme.token.dimension.suite

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.fenix.ui.theme.token.Measurement
import dev.fenix.ui.theme.token.dimension.DimensionSuite

@Immutable
data class CompactSuite(
    override val padding: Measurement = Measurement(),
    override val spacing: Measurement = Measurement(),
    override val margin: Measurement = Measurement(),
    override val icon: Measurement = Measurement(),
    override val button: Measurement = Measurement(
        xs = 12.dp,
        small = 24.dp,
        normal = 44.dp,
        medium = 58.dp,
        large = 64.dp,
        xl =  128.dp,
        xxl =  256.dp
    ),
    override val border: Measurement = Measurement(
        xs = 1.dp
    )
) : DimensionSuite

