package dev.fenix.customer.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.fenix.customer.feature.auth.login.LoginScreen
import dev.fenix.customer.feature.auth.signup.SignUpScreen
import dev.fenix.ui.transition.ConnectTransition
import dev.fenix.customer.core.navigation.Route.*
import dev.fenix.customer.feature.home.HomeScreen

@Composable
fun Navigator(
    startRoute: Route
) {
    val backStack = rememberNavBackStack(startRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.back() },
        transitionSpec = { ConnectTransition.slideRight },
        popTransitionSpec = { ConnectTransition.slideLeft },
        predictivePopTransitionSpec = { ConnectTransition.slideLeft },
        entryProvider = entryProvider {
            entry<Login> {
                LoginScreen(
                    navigateToHome = { backStack.navigateTo(screen = Home) },
                    navigateToSignUp = { backStack.navigateTo(screen = SignUp) }
                )
            }

            entry<SignUp> {
                SignUpScreen(
                    navigateToBack = { backStack.back() },
                    navigateToHome = { backStack.navigateTo(screen = Home) }
                )
            }

            entry<Home> {
                HomeScreen(
                    navigateToLogin = {
                        backStack.resetAndStartFrom(screen = Login)
                    }
                )
            }
        }
    )
}