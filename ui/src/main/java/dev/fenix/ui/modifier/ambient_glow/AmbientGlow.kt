package dev.fenix.ui.modifier.ambient_glow

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import dev.fenix.ui.modifier.ambient_glow.model.Position
import dev.fenix.ui.modifier.ambient_glow.model.Scope

fun Modifier.ambientGlow(
    background: Color,
    configure: Scope.() -> Unit
): Modifier {
    val scope = Scope().apply(configure)

    return this
        .background(background)
        .drawBehind {
            scope.spots.forEach { spot ->
                val xOffset = when (spot.x) {
                    Position.Start -> 0f
                    Position.Center -> size.width / 2f
                    Position.End -> size.width
                }

                val yOffset = when (spot.y) {
                    Position.Start -> 0f
                    Position.Center -> size.height / 2f
                    Position.End -> size.height
                }

                val radialBrush = Brush.radialGradient(
                    colors = listOf(
                        spot.color,
                        spot.color.copy(alpha = 0.5f),
                        spot.color.copy(alpha = 0.15f),
                        Color.Transparent
                    ),
                    radius = size.width * spot.ratio,
                    center = Offset(x = xOffset, y = yOffset)
                )

                drawRect(radialBrush)
            }
        }
}


