package com.zlsp.ppsphb.ui.theme.models

import androidx.compose.runtime.MutableState

class ThemeModeHandler(
    private val isDarkModeState: MutableState<Boolean>,
    private val typographySizeState: MutableState<TypographySize>
) {
    fun updateTheme() {
        isDarkModeState.value = !isDarkModeState.value
    }

    fun updateTypographySize() {
        val typographySize = when(typographySizeState.value) {
            TypographySize.SMALL -> TypographySize.NORMAL
            TypographySize.NORMAL -> TypographySize.BIG
            TypographySize.BIG -> TypographySize.SMALL
        }
        typographySizeState.value = typographySize
    }
}