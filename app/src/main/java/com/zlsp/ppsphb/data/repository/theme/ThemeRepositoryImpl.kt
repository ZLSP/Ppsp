package com.zlsp.ppsphb.data.repository.theme

import com.zlsp.ppsphb.data.utils.UserStorage
import com.zlsp.ppsphb.ui.theme.models.TypographySize
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(private val storage: UserStorage): ThemeRepository {
    override fun getIsDarkMode(): Boolean = storage.getIsDarkMode()

    override fun getTypoSize(): TypographySize = storage.getTypoSize()

    override fun updateThemeMode() = storage.updateThemeMode()

    override fun updateTypoSize() = storage.updateTypoSize()
}