package dev.fenix.ui.core.model

interface Screen<T> {
    val compact: T
    val medium: T
    val expanded: T
    val large: T
    val xl: T
}