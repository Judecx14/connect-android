package dev.fenix.ui.theme.token

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.fenix.ui.core.model.Scale

@Immutable
data class Measurement(
    override val xs: Dp = 4.dp,
    override val small: Dp = 8.dp,
    override val normal: Dp = 16.dp,
    override val medium: Dp = 24.dp,
    override val large: Dp = 32.dp,
    override val xl: Dp = 64.dp,
    override val xxl: Dp = 128.dp
) : Scale<Dp>