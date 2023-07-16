package com.zlsp.ppsphb.ui.theme.models

import androidx.compose.ui.graphics.Color

data class PaletteColor(
    val background: Color,
    val onBackground: Color,
    val secondary: Color,
) {
    companion object {
        private val Blue = Color(0xFF080c17)
        private val Red = Color(0xFFd5292c)

        fun getPalette(isDark: Boolean) : PaletteColor = PaletteColor(
            background = if (isDark) Blue else Color.White,
            onBackground = if (isDark) Color.White else Blue,
            secondary = Red
        )
    }
}