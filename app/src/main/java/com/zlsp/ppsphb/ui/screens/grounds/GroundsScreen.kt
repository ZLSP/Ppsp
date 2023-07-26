package com.zlsp.ppsphb.ui.screens.grounds

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
import com.zlsp.ppsphb.data.repository.grounds.model.GroundsResponse
import com.zlsp.ppsphb.ui.general.DefaultScreenWrapper
import com.zlsp.ppsphb.ui.general.ViewArticleItem
import com.zlsp.ppsphb.ui.general.ViewCardCustomElevation
import com.zlsp.ppsphb.ui.general.ViewPartItem
import com.zlsp.ppsphb.ui.general.ViewPointItem
import com.zlsp.ppsphb.ui.theme.AppTheme
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun GroundsScreen(
    state: GroundsScreenState,
    sendEvent: (GroundsScreenEvent) -> Unit,
) {
    DefaultScreenWrapper(
        contentState = state.contentState,
        onClickButtonError = { sendEvent(GroundsScreenEvent.Init) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
        ) {
            items(
                items = state.listGrounds,
                key = { it.id },
                itemContent = {
                    GroundsItem(
                        ground = it,
                        onClickItem = { sendEvent(GroundsScreenEvent.OnClickItem) }
                    )
                }
            )
        }
    }
}

@Composable
private fun GroundsItem(
    ground: GroundsResponse,
    onClickItem: () -> Unit
) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = ground.title,
                style = Theme.typography.titleScreen.copy(textAlign = TextAlign.Center),
                color = Theme.colors.onBackground
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = ground.subTitle,
                style = Theme.typography.titleArticle.copy(textAlign = TextAlign.Center),
                color = Theme.colors.onBackground
            )
            AnimatedVisibility(
                visible = isExpand,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(Modifier.fillMaxWidth()) {
                    ground.listArticles.forEach { article ->
                        ViewArticleItem(article)
                        article.listParts?.forEach { part ->
                            ViewPartItem(part)
                            part.points?.let { points ->
                                points.forEach { point ->
                                    ViewPointItem(
                                        pointText = point,
                                        backgroundColor = Theme.colors.onBackground
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun PreviewDark() = AppTheme() {
    GroundsScreen(
        state = GroundsScreenState.getPreview(),
        sendEvent = {}
    )
}

@Composable
@Preview
private fun PreviewLight() = AppTheme(false) {
    GroundsScreen(
        state = GroundsScreenState.getPreview(),
        sendEvent = {}
    )
}