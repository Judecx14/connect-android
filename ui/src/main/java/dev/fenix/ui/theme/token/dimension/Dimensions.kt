package dev.fenix.ui.theme.token.dimension

import androidx.compose.runtime.Immutable
import dev.fenix.ui.core.model.Screen
import dev.fenix.ui.theme.token.Breakpoints
import dev.fenix.ui.theme.token.dimension.suite.CompactSuite

@Immutable
data class Dimension(
    override val compact: DimensionSuite = CompactSuite(),
    override val medium: DimensionSuite = CompactSuite(),
    override val expanded: DimensionSuite = CompactSuite(),
    override val large: DimensionSuite = CompactSuite(),
    override val xl: DimensionSuite = CompactSuite(),
) : Screen<DimensionSuite>


fun Dimension.suiteFor(screenWidth: Int): DimensionSuite = when {
    screenWidth < Breakpoints.compact.width  -> compact
    screenWidth < Breakpoints.medium.width   -> medium
    screenWidth < Breakpoints.expanded.width -> expanded
    screenWidth < Breakpoints.large.width    -> large
    else                                     -> xl
}

val Dimensions = Dimension()