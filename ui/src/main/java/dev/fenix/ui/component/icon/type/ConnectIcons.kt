package dev.fenix.ui.component.icon.type

import dev.fenix.ui.R

sealed class ConnectIcons(val resId: Int) {
    object ChevronLeft : ConnectIcons(R.drawable.ic_chevron_left)
    object UserRounded : ConnectIcons(R.drawable.ic_user_round)
    object Bell : ConnectIcons(R.drawable.ic_bell)
    object Locate : ConnectIcons(R.drawable.ic_locate)
    object Bolt : ConnectIcons(R.drawable.ic_bolt)
    object House : ConnectIcons(R.drawable.ic_house)
    object ListRestart : ConnectIcons(R.drawable.ic_list_restart)
    object Logout : ConnectIcons(R.drawable.ic_log_out)
    object Zap : ConnectIcons(R.drawable.ic_zap)
    object Store : ConnectIcons(R.drawable.ic_store)
    object StickyNotePlus : ConnectIcons(R.drawable.ic_sticky_note_plus)
}