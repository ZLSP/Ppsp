package com.zlsp.ppsphb.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.staticCompositionLocalOf
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zlsp.ppsphb.base.switch
import com.zlsp.ppsphb.ui.theme.models.AppTypography
import com.zlsp.ppsphb.ui.theme.models.PaletteColor
import com.zlsp.ppsphb.ui.theme.models.ThemeModeHandler
import com.zlsp.ppsphb.ui.theme.models.TypographySize

object Theme {
    val colors: PaletteColor
        @Composable
        get() = LocalPaletteColor.current

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
}

@Composable
fun AppTheme(
    isDarkMode: Boolean = true,
    typoSize: TypographySize = TypographySize.SMALL,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val isDarkThemeState = rememberSaveable { mutableStateOf(isDarkMode) }
    val typoSizeState = rememberSaveable { mutableStateOf(typoSize) }
    val paletteColor = PaletteColor.getPalette(isDarkThemeState.value).switch()
    val typography = AppTypography.getTypography(
        size = typoSizeState.value,
        paletteColor = paletteColor
    )
    val systemBarsColor = paletteColor.background
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = systemBarsColor,
            darkIcons = !isDarkThemeState.value
        )
    }
    CompositionLocalProvider(
        LocalPaletteColor provides paletteColor,
        LocalAppTypography provides typography,
        LocalThemeMode provides ThemeModeHandler(isDarkThemeState, typoSizeState),
        content = content
    )
}

private val LocalPaletteColor = staticCompositionLocalOf<PaletteColor> {
    error("No PaletteColor provided")
}

private val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("No AppTypography provided")
}

val LocalThemeMode = staticCompositionLocalOf<ThemeModeHandler> {
    error("No ThemeMode provided")
}