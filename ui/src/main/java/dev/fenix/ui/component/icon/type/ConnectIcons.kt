package dev.fenix.ui.component.icon.type

import dev.fenix.ui.R

sealed class ConnectIcons(val resId: Int) {
    object ChevronLeft : ConnectIcons(R.drawable.ic_chevron_left)
}