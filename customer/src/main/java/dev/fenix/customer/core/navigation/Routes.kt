package dev.fenix.customer.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route : NavKey {
    @Serializable
    data object Login : Route()

    @Serializable
    data object SignUp : Route()

    @Serializable
    data object Home: Route()

}