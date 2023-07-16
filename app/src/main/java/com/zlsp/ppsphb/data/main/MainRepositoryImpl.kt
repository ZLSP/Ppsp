package com.zlsp.ppsphb.data.main

import com.zlsp.ppsphb.data.UserStorage
import com.zlsp.ppsphb.ui.theme.models.TypographySize
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val storage: UserStorage): MainRepository {
    override fun getIsDarkMode(): Boolean = storage.getIsDarkMode()

    override fun getTypoSize(): TypographySize = storage.getTypoSize()

    override fun updateThemeMode() = storage.updateThemeMode()

    override fun updateTypoSize() = storage.updateTypoSize()
}