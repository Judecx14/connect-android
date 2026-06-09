package dev.fenix.customer.feature.hub.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.component.app_bar.navigation_bar.ConnectNavigationBar
import dev.fenix.ui.component.icon.type.ConnectIcons
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun BottomBar() {
    ConnectNavigationBar(tonalElevation = ConnectTheme.dimensions.elevation.xs) {
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
}

@Preview
@Composable
private fun BottomBarPreview() {
    ConnectTheme {
        BottomBar()
    }
}