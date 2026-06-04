package dev.fenix.ui.modifier.ambient_glow.model

import androidx.compose.ui.graphics.Color

data class GlowSpot(
    val color: Color,
    val x: Position,
    val y: Position,
    val ratio: Float
)

class Scope {
    internal val spots = mutableListOf<GlowSpot>()

    fun spot(
        color: Color,
        x: Position,
        y: Position,
        ratio: Float
    ) {
        spots.add(GlowSpot(color, x, y, ratio))
    }
}