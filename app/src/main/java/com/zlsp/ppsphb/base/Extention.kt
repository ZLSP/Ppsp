package com.zlsp.ppsphb.base

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.zlsp.ppsphb.ui.theme.models.PaletteColor

@Composable
fun PaletteColor.switch() : PaletteColor = this.copy(
    background = animateColor(this.background),
    onBackground = animateColor(this.onBackground),
    secondary = animateColor(this.secondary)
)

@Composable
private fun animateColor(targetColor: Color) =
    animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(durationMillis = 1000)
    ).value