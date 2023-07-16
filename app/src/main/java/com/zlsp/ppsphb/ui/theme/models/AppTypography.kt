package com.zlsp.ppsphb.ui.theme.models

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class AppTypography(
    val title: TextStyle
) {
    companion object {
        fun getTypography(size: TypographySize, paletteColor: PaletteColor) : AppTypography = when(size) {
            TypographySize.SMALL -> AppTypography(
                title = TextStyle(
                    color = paletteColor.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                )
            )
            TypographySize.NORMAL -> AppTypography(
                title = TextStyle(
                    color = paletteColor.onBackground,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.W700,
                )
            )
            TypographySize.BIG -> AppTypography(
                title = TextStyle(
                    color = paletteColor.onBackground,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W700,
                )
            )
        }
    }
}