package dev.fenix.customer.feature.hub

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.fenix.ui.component.app_bar.ConnectTopBar
import dev.fenix.ui.component.button.ConnectIconButton
import dev.fenix.ui.component.button.type.Variant
import dev.fenix.ui.component.icon.ConnectIcon
import dev.fenix.ui.component.icon.type.ConnectIcons
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.component.text.type.ConnectTextStyle
import dev.fenix.ui.modifier.ambient_glow.ambientGlow
import dev.fenix.ui.modifier.ambient_glow.model.Position
import dev.fenix.ui.theme.ConnectTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UserInformation() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            ConnectTheme.dimensions.spacing.small
        )
    ) {
        ConnectIconButton(
            onClick = {}
        ) {
            ConnectIcon(icon = ConnectIcons.UserRounded)
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                ConnectTheme.dimensions.spacing.xs
            ),
        ) {
            ConnectText(
                text = "User name",
                style = ConnectTextStyle.Label,
                color = ConnectTheme.colors.inverseOnSurface
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(ConnectTheme.dimensions.spacing.xs)
            ) {
                ConnectIcon(
                    icon = ConnectIcons.Locate,
                    modifier = Modifier.size(ConnectTheme.dimensions.icon.medium),
                    tint = ConnectTheme.colors.inverseOnSurface
                )

                ConnectText(
                    text = "Current location",
                    color = ConnectTheme.colors.inverseOnSurface
                )
            }
        }
    }
}

@Composable
private fun Content(
    logout: () -> Unit,
) {
    val colorPrimary = ConnectTheme.colors.primary

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier.ambientGlow(
            background = ConnectTheme.colors.background,
        ) {
            spot(
                color = colorPrimary,
                x = Position.Center,
                y = Position.Start,
                ratio = 1.75f
            )
        },
        topBar = {
            ConnectTopBar(
                leading = {
                    UserInformation()
                },
                trailing = {
                    ConnectIconButton(
                        variant = Variant.Ghost,
                        onClick = {}
                    ) {
                        ConnectIcon(
                            icon = ConnectIcons.Bell,
                            tint = ConnectTheme.colors.inverseOnSurface
                        )
                    }
                }
            )
        },
        // TODO Extract to ui library
        bottomBar = {
            BottomAppBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { ConnectIcon(icon = ConnectIcons.House) },
                    label = {
                        ConnectText(text = "Home")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { ConnectIcon(icon = ConnectIcons.ListRestart) },
                    label = {
                        ConnectText(text = "Requests")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { ConnectIcon(icon = ConnectIcons.Bolt) },
                    label = {
                        ConnectText(text = "Settings")
                    }
                )
            }
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = logout
            ) {
                ConnectIcon(icon = ConnectIcons.Logout)
            }
        }

    ) { _ ->

    }
}

@Composable
fun HubScreen(
    hubViewModel: HubViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {
    LaunchedEffect(Unit) {
        hubViewModel.effects.collectLatest { effect ->
            when (effect) {
                HomeUiEffect.FailureLogout -> {}
                HomeUiEffect.SuccessLogout -> navigateToLogin()
            }
        }
    }

    Content(logout = hubViewModel::doLogout)
}

@Preview
@Composable
private fun HubScreenPreview() {
    ConnectTheme {
        Content(
            logout = {}
        )
    }
}