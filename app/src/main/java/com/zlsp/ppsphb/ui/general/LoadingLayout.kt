package com.zlsp.ppsphb.ui.general

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun BoxScope.LoadingLayout() {
    BoxHideContent {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Center),
            color = Theme.colors.secondary
        )
    }
}