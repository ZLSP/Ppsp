package com.zlsp.ppsphb.base

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zlsp.ppsphb.ui.theme.models.PaletteColor
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun PaletteColor.switch(): PaletteColor = this.copy(
    background = animateColor(this.background),
    onBackground = animateColor(this.onBackground),
    secondary = animateColor(this.secondary)
)

@Composable
fun animateColor(targetColor: Color): Color {
    return animateColorAsState(targetColor, tween(durationMillis = 1000)).value
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