package dev.fenix.ui.theme.token.font

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import dev.fenix.ui.core.model.Scale

data class FontSize(
    override val xs: TextUnit  = 6.sp,
    override val small: TextUnit  = 8.sp,
    override val normal: TextUnit  = 14.sp,
    override val medium: TextUnit  = 16.sp,
    override val large: TextUnit  = 24.sp,
    override val xl: TextUnit  = 32.sp,
    override val xxl: TextUnit  = 44.sp,
) : Scale<TextUnit>

val DefaultFontSize = FontSize()