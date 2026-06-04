package dev.fenix.ui.theme.token

import androidx.compose.runtime.Immutable
import dev.fenix.ui.core.model.Screen
import dev.fenix.ui.core.model.Size

@Immutable
data class Breakpoint(
    override val compact: Size<Int> = Size(width = 600, height = null),
    override val medium: Size<Int> = Size(width = 840, height = null),
    override val expanded: Size<Int> = Size(width = 1200, height = null),
    override val large: Size<Int> = Size(width = 1600, height = null),
    override val xl: Size<Int> = Size(width = 1920, height = null),
) : Screen<Size<Int>>

val Breakpoints = Breakpoint()