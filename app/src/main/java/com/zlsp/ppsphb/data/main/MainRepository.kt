package com.zlsp.ppsphb.data.main

import com.zlsp.ppsphb.ui.theme.models.TypographySize

interface MainRepository {
    fun getIsDarkMode(): Boolean
    fun getTypoSize(): TypographySize
    fun updateThemeMode()
    fun updateTypoSize()
}