package dev.fenix.ui.transition

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith

object ConnectTransition {

    private const val DURATION = 300

    val slideRight = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(durationMillis = DURATION)
    ) togetherWith slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(durationMillis = DURATION)
    )

    val slideLeft = slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(durationMillis = DURATION)
    ) togetherWith slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(durationMillis = DURATION)
    )

}