package dev.fenix.ui.component.app_bar.navigation_bar.model

import dev.fenix.ui.component.icon.type.ConnectIcons

data class ConnectNavigationBarItem(
    val label: String,
    val icon: ConnectIcons,
    val selected: Boolean,
    val showLabel: Boolean,
    val onClick: () -> Unit
)

class Scope {
    private val MAX_ITEMS_LENGTH = 4

    private val _items = mutableListOf<ConnectNavigationBarItem>()
    val items: List<ConnectNavigationBarItem> = _items

    fun item(
        label: String,
        icon: ConnectIcons,
        selected: Boolean,
        showLabel: Boolean,
        onClick: () -> Unit
    ) {
        if (_items.size == MAX_ITEMS_LENGTH) return

        _items.add(
            ConnectNavigationBarItem(
                label = label,
                icon = icon,
                selected = selected,
                showLabel = showLabel,
                onClick = onClick
            )
        )
    }
}