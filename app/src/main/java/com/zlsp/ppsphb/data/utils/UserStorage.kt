package com.zlsp.ppsphb.data.utils

import android.content.SharedPreferences
import com.zlsp.ppsphb.ui.theme.models.TypographySize
import timber.log.Timber
import javax.inject.Inject

class UserStorage @Inject constructor(private val pref: SharedPreferences) {
    companion object {
        const val KEY_THEME_MODE = "themeMode"
        const val KEY_TYPO_SIZE = "typoSize"
        const val KEY_FULL_AD = "full_ad"
    }

    fun updateThemeMode() {
        val isDarkMode = getIsDarkMode()
        pref.edit().putBoolean(KEY_THEME_MODE, !isDarkMode).apply()
    }

    fun updateTypoSize() {
        val nowSize = when(getTypoSize()) {
            TypographySize.SMALL -> 1
            TypographySize.NORMAL -> 2
            TypographySize.BIG -> 0
        }
        pref.edit().putInt(KEY_TYPO_SIZE, nowSize).apply()
    }

    fun getIsDarkMode() = pref.getBoolean(KEY_THEME_MODE, true)

    fun getTypoSize() =
        TypographySize.values().firstOrNull { it.id == pref.getInt(KEY_TYPO_SIZE, 0) }
            ?: TypographySize.SMALL


    fun checkIsShowAd(): Boolean {
        val numAd: Int = pref.getInt(KEY_FULL_AD, 1)
        val isShowAd = numAd > 8
        Timber.tag("Yandex").d("Number: $numAd; isShowAd: $isShowAd")
        return if (isShowAd) {
            updateAdState(1)
            true
        } else {
            updateAdState(numAd + 1)
            false
        }
    }

    private fun updateAdState(num: Int) {
        pref.edit().putInt(KEY_FULL_AD, num).apply()
    }
}