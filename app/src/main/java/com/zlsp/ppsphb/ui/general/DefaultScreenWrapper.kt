package com.zlsp.ppsphb.ui.general

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zlsp.ppsphb.base.ContentState

@Composable
fun DefaultScreenWrapper(
    contentState: ContentState,
    onClickButtonError: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        content()
        when (contentState) {
            is ContentState.Content -> {}
            is ContentState.Error -> ErrorLayout(
                title = contentState.text,
                onClick = onClickButtonError
            )

            is ContentState.Loading -> LoadingLayout()
        }
    }
}