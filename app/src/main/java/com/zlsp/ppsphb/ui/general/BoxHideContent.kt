package com.zlsp.ppsphb.ui.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun BoxHideContent(content: @Composable () -> Unit) =
    Box(
        Modifier
            .fillMaxSize()
            .background(Theme.colors.background)
    ) {
        content()
    }