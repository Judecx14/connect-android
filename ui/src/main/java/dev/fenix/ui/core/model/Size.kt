package dev.fenix.ui.core.model

import androidx.compose.runtime.Immutable

@Immutable
data class Size<T> (
    val width: T,
    val height: T?
)