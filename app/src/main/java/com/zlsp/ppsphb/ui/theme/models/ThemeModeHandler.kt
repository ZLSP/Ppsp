package com.zlsp.ppsphb.ui.theme.models

import androidx.compose.runtime.MutableState

class ThemeModeHandler(private val isDarkModeState: MutableState<Boolean>) {
    fun setMode(isDark: Boolean) {
        isDarkModeState.value = isDark
    }
}