package com.zlsp.ppsphb.base

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zlsp.ppsphb.ui.theme.models.AppTypography
import com.zlsp.ppsphb.ui.theme.models.PaletteColor
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun PaletteColor.switch(): PaletteColor = this.copy(
    background = animateColor(this.background),
    onBackground = animateColor(this.onBackground),
    secondary = animateColor(this.secondary)
)

@Composable
fun AppTypography.switch(): AppTypography = this.copy(
    titleScreen = animateSizes(titleScreen),
    numArticle = animateSizes(numArticle),
    titleArticle = animateSizes(titleArticle),
    titlePart = animateSizes(titlePart),
    textBody = animateSizes(textBody),
    button = animateSizes(button)
)

@Composable
fun animateColor(targetColor: Color): Color {
    return animateColorAsState(targetColor, tween(durationMillis = 1000)).value
}

@Composable
fun animateSizes(style: TextStyle): TextStyle {
    val animateSize = animateFloatAsState(targetValue = style.fontSize.value)
    val animateWeight = animateIntAsState(targetValue = style.fontWeight?.weight?: 100)
    return style.copy(fontSize = animateSize.value.sp, fontWeight = FontWeight(animateWeight.value))
}

fun <State : MVI.State, Effect : MVI.Effect, Event : MVI.Event> NavGraphBuilder.baseComposable(
    route: String,
    getViewModel: @Composable () -> BaseViewModel<State, Effect, Event>,
    content: @Composable (BaseViewModel<State, Effect, Event>) -> Unit
) {
    composable(route) {
        val viewModel = getViewModel.invoke()
        val state by viewModel.collectAsState()
        val sendEvent = viewModel::sendEvent
        content(getViewModel.invoke())
    }
}