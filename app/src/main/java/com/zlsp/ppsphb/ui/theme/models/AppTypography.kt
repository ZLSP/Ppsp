package com.zlsp.ppsphb.ui.theme.models

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class AppTypography(
    val titleScreen: TextStyle,
    val numArticle: TextStyle,
    val titleArticle: TextStyle,
    val textBody: TextStyle,
    val titlePart: TextStyle,
    val button: TextStyle
) {
    companion object {
        fun getTypography(size: TypographySize, paletteColor: PaletteColor): AppTypography {
            val defStyle = TextStyle(
                color = paletteColor.onBackground
            )
            val title = defStyle.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.W700,
            )
            val numArticle = defStyle.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )
            val titleArticle = defStyle.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )
            val textBody = defStyle.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400
            )
            val partTitle = defStyle.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.W500
            )
            val button = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W700
            )
            return when (size) {
                TypographySize.SMALL -> AppTypography(
                    titleScreen = title,
                    numArticle = numArticle,
                    titleArticle = titleArticle,
                    textBody = textBody,
                    titlePart = partTitle,
                    button = button,
                )

                TypographySize.NORMAL -> AppTypography(
                    titleScreen = title.copy(fontSize = 22.sp),
                    numArticle = numArticle.copy(fontSize = 21.sp),
                    titleArticle = titleArticle.copy(fontSize = 21.sp),
                    textBody = textBody.copy(fontSize = 17.sp),
                    titlePart = partTitle.copy(fontSize = 17.sp),
                    button = button.copy(fontSize = 19.sp),
                )

                TypographySize.BIG -> AppTypography(
                    titleScreen = title.copy(fontSize = 24.sp),
                    numArticle = numArticle.copy(fontSize = 24.sp),
                    titleArticle = titleArticle.copy(fontSize = 24.sp),
                    textBody = textBody.copy(fontSize = 20.sp),
                    titlePart = partTitle.copy(fontSize = 20.sp),
                    button = button.copy(fontSize = 22.sp),
                )
            }
        }
    }
}