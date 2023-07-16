package com.zlsp.ppsphb.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.staticCompositionLocalOf
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zlsp.ppsphb.base.switch
import com.zlsp.ppsphb.ui.theme.models.PaletteColor
import com.zlsp.ppsphb.ui.theme.models.ThemeModeHandler

object Theme {
    val colors: PaletteColor
        @Composable
        get() = LocalPaletteColor.current

    val mode: ThemeModeHandler
        @Composable
        get() = LocalThemeMode.current
}

@Composable
fun AppTheme(
    isDarkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val isDarkThemeState = rememberSaveable { mutableStateOf(isDarkTheme) }
    val paletteColor = PaletteColor.getPalette(isDarkThemeState.value).switch()
    val systemBarsColor = paletteColor.background
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = systemBarsColor,
            darkIcons = !isDarkThemeState.value
        )
    }
    CompositionLocalProvider(
        LocalPaletteColor provides paletteColor,
        LocalThemeMode provides ThemeModeHandler(isDarkThemeState),
        content = content
    )
}

private val LocalPaletteColor = staticCompositionLocalOf<PaletteColor> {
    error("No PaletteColor provided")
}

private val LocalThemeMode = staticCompositionLocalOf<ThemeModeHandler> {
    error("No PaletteColor provided")
}