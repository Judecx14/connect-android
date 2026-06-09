package dev.fenix.customer.feature.hub

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.fenix.ui.component.app_bar.ConnectTopBar
import dev.fenix.ui.component.app_bar.navigation_bar.ConnectNavigationBar
import dev.fenix.ui.component.button.ConnectIconButton
import dev.fenix.ui.component.button.type.Variant
import dev.fenix.ui.component.icon.ConnectIcon
import dev.fenix.ui.component.icon.type.ConnectIcons
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.component.text.type.ConnectTextStyle
import dev.fenix.ui.modifier.ambient_glow.ambientGlow
import dev.fenix.ui.modifier.ambient_glow.model.Position
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.theme.token.color_scheme.Colors
import kotlinx.coroutines.flow.collectLatest


@Composable
private fun TopBar() {
    val contentColor = Colors.Dark.onSurface

    ConnectTopBar(
        leading = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    ConnectTheme.dimensions.spacing.small
                )
            ) {
                ConnectIconButton(
                    onClick = {}
                ) {
                    ConnectIcon(icon = ConnectIcons.UserRounded, tint = contentColor)
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        ConnectTheme.dimensions.spacing.xs
                    ),
                ) {
                    ConnectText(
                        text = "User name",
                        style = ConnectTextStyle.Label,
                        color = contentColor
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(ConnectTheme.dimensions.spacing.xs)
                    ) {
                        ConnectIcon(
                            icon = ConnectIcons.Locate,
                            modifier = Modifier.size(ConnectTheme.dimensions.icon.medium),
                            tint = contentColor
                        )

                        ConnectText(
                            text = "Current location",
                            color = contentColor
                        )
                    }
                }
            }
        },
        trailing = {
            ConnectIconButton(
                variant = Variant.Ghost,
                onClick = {}
            ) {
                ConnectIcon(
                    icon = ConnectIcons.Bell,
                    tint = contentColor
                )
            }
        }
    )
}

@Composable
private fun Content(
    logout: () -> Unit,
) {
    val colorPrimary = ConnectTheme.colors.primary

    Scaffold(
        modifier = Modifier
            .ambientGlow(background = ConnectTheme.colors.background) {
                spot(
                    color = colorPrimary,
                    x = Position.Start,
                    y = Position.Start,
                    ratio = 1.25f
                )

                spot(
                    color = colorPrimary,
                    x = Position.End,
                    y = Position.Start,
                    ratio = 0.65f
                )
            },
        containerColor = Color.Transparent,
        topBar = { TopBar() },
        bottomBar = {
            ConnectNavigationBar {
                item(
                    label = "Home",
                    icon = ConnectIcons.House,
                    selected = true,
                    showLabel = true,
                    onClick = {}
                )

                item(
                    label = "Requests",
                    icon = ConnectIcons.ListRestart,
                    selected = false,
                    showLabel = false,
                    onClick = {}
                )

                item(
                    label = "Settings",
                    icon = ConnectIcons.Bolt,
                    selected = false,
                    showLabel = false,
                    onClick = {}
                )
            }
        },
    ) { _ ->
        ConnectText(text = "")
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun HubScreenPreview() {
    ConnectTheme {
        Content(
            logout = {}
        )
    }
}