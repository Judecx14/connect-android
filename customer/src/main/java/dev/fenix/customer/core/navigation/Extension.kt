package dev.fenix.customer.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun NavBackStack<NavKey>.navigateTo(screen: NavKey) {
    add(screen)
}

fun NavBackStack<NavKey>.back() {
    if (size <= 1) return

    removeLastOrNull()
}

fun NavBackStack<NavKey>.backTo(targetScreen: NavKey) {
    if (size <= 1) return

    if (targetScreen !in this) return

    while (isNotEmpty() && last() != targetScreen) {
        removeLastOrNull()
    }
}