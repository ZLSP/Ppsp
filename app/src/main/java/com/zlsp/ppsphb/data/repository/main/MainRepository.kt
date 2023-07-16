package com.zlsp.ppsphb.data.repository.main

import com.zlsp.ppsphb.ui.theme.models.TypographySize

interface MainRepository {
    fun getIsDarkMode(): Boolean
    fun getTypoSize(): TypographySize
    fun updateThemeMode()
    fun updateTypoSize()
}