package com.zlsp.ppsphb.ui.screens.authority

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.ui.general.DefaultScreenWrapper
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun AuthorityScreen(
    state: AuthorityScreenState,
    sendEvent: (AuthorityScreenEvent) -> Unit
) {
    DefaultScreenWrapper(
        contentState = state.contentState,
        onClickButtonError = { sendEvent(AuthorityScreenEvent.Init) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
        ) {
            item {
                Text(
                    text = state.titleContent,
                    style = Theme.typography.titleArticle,
                    color = Theme.colors.onBackground
                )
            }

            items(
                items = state.authorityList,
                key = { it.id }
            ) {

            }
        }
    }
}