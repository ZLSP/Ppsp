package com.zlsp.ppsphb.ui.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.ui.theme.LocalThemeMode
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun ViewCardCustomElevation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Theme.colors.background,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    val isDarkMode = LocalThemeMode.current.isDarkMode
    val modifierShadow = if (isDarkMode) {
        Modifier.shadow(
            elevation = 20.dp,
            spotColor = Color.White,
            shape = shape
        )
    } else {
        Modifier
    }
    val elevation = if (isDarkMode) 0.dp else 15.dp
    Card(
        modifier = modifierShadow.then(modifier),
        backgroundColor = backgroundColor,
        contentColor = Theme.colors.onBackground,
        elevation = elevation,
        shape = shape,
        border = border
    ) {
        content()
    }
}