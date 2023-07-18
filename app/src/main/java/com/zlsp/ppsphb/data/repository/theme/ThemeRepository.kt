package com.zlsp.ppsphb.data.repository.theme

import com.zlsp.ppsphb.ui.theme.models.TypographySize

interface ThemeRepository {
    fun getIsDarkMode(): Boolean
    fun getTypoSize(): TypographySize
    fun updateThemeMode()
    fun updateTypoSize()
}