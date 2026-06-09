package dev.fenix.customer.feature.hub.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.component.app_bar.ConnectTopBar
import dev.fenix.ui.component.button.ConnectIconButton
import dev.fenix.ui.component.button.type.Variant
import dev.fenix.ui.component.icon.ConnectIcon
import dev.fenix.ui.component.icon.type.ConnectIcons
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.component.text.type.ConnectTextStyle
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.theme.token.color_scheme.Colors

@Composable
fun TopBar() {
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
                    onClick = { },
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


@Preview
@Composable
private fun TopBarPreview() {
    ConnectTheme {
        TopBar()
    }
}