package com.zlsp.ppsphb.ui.screens.authority

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.data.repository.police_act.models.ActArticleResponse
import com.zlsp.ppsphb.ui.general.DefaultScreenWrapper
import com.zlsp.ppsphb.ui.general.ViewArticleItem
import com.zlsp.ppsphb.ui.general.ViewCardCustomElevation
import com.zlsp.ppsphb.ui.general.ViewPartItem
import com.zlsp.ppsphb.ui.general.ViewPointItem
import com.zlsp.ppsphb.ui.theme.AppTheme
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
                    style = Theme.typography.titlePart,
                    color = Theme.colors.onBackground
                )
            }

            items(
                items = state.authorityList,
                key = { it.id }
            ) {
                AuthorityItem(
                    article = it,
                    onClickItem = { sendEvent(AuthorityScreenEvent.OnClickItem) }
                )
            }
        }
    }
}

@Composable
private fun AuthorityItem(
    article: ActArticleResponse,
    onClickItem: () -> Unit
) {
    Spacer(Modifier.height(10.dp))
    var isExpand by remember { mutableStateOf(false) }
    Spacer(Modifier.height(10.dp))
    ViewCardCustomElevation(
        modifier = Modifier.clickable {
            isExpand = !isExpand
            onClickItem()
        },
        border = BorderStroke(1.dp, Theme.colors.secondary)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = article.numArticle,
                style = Theme.typography.titleArticle.copy(textAlign = TextAlign.Center),
                color = Theme.colors.onBackground
            )
            AnimatedVisibility(
                visible = isExpand,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(Modifier.fillMaxWidth()) {
                    ViewArticleItem(article)
                    article.listParts?.forEach { part ->
                        ViewPartItem(part)
                        part.points?.let {
                            ViewPointItem(
                                pointText = it.first(),
                                backgroundColor = Theme.colors.secondary
                            )
                        }
                    }
                    article.notes?.let {
                        Spacer(Modifier.height(10.dp))
                        Divider(color = Theme.colors.onBackground.copy(0.1f))
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = it.title,
                            style = Theme.typography.titlePart
                        )
                        Spacer(Modifier.height(10.dp))
                        Divider(color = Theme.colors.onBackground.copy(0.1f))
                        Spacer(Modifier.height(5.dp))
                        it.listNotes.forEach { note ->
                            ViewPointItem(
                                pointText = note,
                                backgroundColor = Theme.colors.onBackground
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun PreviewDark() = AppTheme {
    AuthorityScreen(state = AuthorityScreenState.getPreview(), sendEvent = {})
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
private fun PreviewLight() = AppTheme(false) {
    AuthorityScreen(state = AuthorityScreenState.getPreview(), sendEvent = {})
}
