package dev.fenix.ui.component.app_bar.navigation_bar

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.fenix.ui.component.app_bar.navigation_bar.model.Scope
import dev.fenix.ui.component.icon.ConnectIcon
import dev.fenix.ui.component.icon.type.ConnectIcons
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun ConnectNavigationBar(
    modifier: Modifier = Modifier,
    background: Color = ConnectTheme.colors.surface,
    tonalElevation: Dp = 0.dp,
    configure: Scope.() -> Unit
) {
    val scope = Scope().apply(configure)

    NavigationBar(
        modifier = modifier
            .navigationBarsPadding()
            .padding(
                horizontal = ConnectTheme.dimensions.padding.normal,
            )
            .clip(ConnectTheme.shapes.extraLarge),
        containerColor = background,
        tonalElevation = tonalElevation
    ) {
        scope.items.forEach { item ->
            NavigationBarItem(
                label = { ConnectText(text = item.label) },
                icon = { ConnectIcon(icon = item.icon) },
                alwaysShowLabel = item.selected,
                selected = item.selected,
                onClick = item.onClick,
                colors = NavigationBarItemColors(
                    selectedIconColor = ConnectTheme.colors.primary,
                    selectedTextColor = ConnectTheme.colors.primary,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = ConnectTheme.colors.onSurface,
                    unselectedTextColor = ConnectTheme.colors.onSurface,
                    disabledIconColor = ConnectTheme.colors.onSurfaceVariant,
                    disabledTextColor = ConnectTheme.colors.onSurfaceVariant,
                )
            )
        }
    }
}

@Preview
@Composable
private fun ConnectNavigationBarPreview() {
    ConnectTheme {
        ConnectNavigationBar {
            item(
                label = "House",
                icon = ConnectIcons.House,
                selected = true,
                showLabel = true,
                onClick = {}
            )

            item(
                label = "Settings",
                icon = ConnectIcons.Bolt,
                selected = false,
                showLabel = false,
                onClick = {}
            )

            item(
                label = "List",
                icon = ConnectIcons.ListRestart,
                selected = false,
                showLabel = false,
                onClick = {}
            )

            item(
                label = "Exit",
                icon = ConnectIcons.Logout,
                selected = false,
                showLabel = false,
                onClick = {}
            )
        }
    }
}