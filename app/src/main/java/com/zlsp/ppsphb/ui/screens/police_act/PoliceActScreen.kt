package com.zlsp.ppsphb.ui.screens.police_act

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.data.repository.police_act.models.ActArticleResponse
import com.zlsp.ppsphb.data.repository.police_act.models.PartArticleResponse
import com.zlsp.ppsphb.ui.general.DefaultScreenWrapper
import com.zlsp.ppsphb.ui.general.ViewCardCustomElevation
import com.zlsp.ppsphb.ui.general.ViewPointItem
import com.zlsp.ppsphb.ui.theme.AppTheme
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun PoliceActScreen(
    state: PoliceActScreenState,
    isVisibleUiBars: Boolean,
    sendEvent: (PoliceActScreenEvent) -> Unit,
) {
    DefaultScreenWrapper(
        contentState = state.contentState,
        onClickButtonError = { sendEvent(PoliceActScreenEvent.Init) }
    ) {
        Row {
            ListParts(state.activeArticle)
            ListArticlesNumber(
                activeArticleId = state.activeArticle.id,
                isVisible = isVisibleUiBars,
                listArticle = state.listArticles,
                onClickItem = { sendEvent(PoliceActScreenEvent.OnClickArticle(it)) }
            )
        }
    }
}

@Composable
private fun ListArticlesNumber(
    activeArticleId: Double,
    isVisible: Boolean,
    listArticle: List<ActArticleResponse>,
    onClickItem: (ActArticleResponse) -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = expandHorizontally(),
        exit = shrinkHorizontally(),
    ) {
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
            color = Theme.colors.secondary
        )
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            contentPadding = PaddingValues(horizontal = 5.dp)
        ) {
            item { Spacer(Modifier.height(10.dp)) }
            items(
                items = listArticle,
                key = { it.id },
                itemContent = {
                    ArticleNumItem(
                        actArticleResponse = it,
                        isActive = it.id == activeArticleId,
                        onClickItem = { onClickItem(it) }
                    )
                }
            )
            item { Spacer(Modifier.height(10.dp)) }
        }
    }
}

@Composable
private fun ArticleNumItem(
    actArticleResponse: ActArticleResponse,
    isActive: Boolean,
    onClickItem: () -> Unit
) {
    val background = if (isActive) {
        Theme.colors.secondary
    } else {
        Theme.colors.background
    }
    Spacer(Modifier.height(10.dp))
    ViewCardCustomElevation(
        modifier = Modifier.clickable { onClickItem() },
        backgroundColor = background,
        shape = RoundedCornerShape(6.dp)
    ) {
        Box(Modifier.size(50.dp)) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = actArticleResponse.numArticle,
                style = Theme.typography.numArticle
            )
        }
    }
}

@Composable
private fun RowScope.ListParts(article: ActArticleResponse) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        item {
            ListTitle(article.titleArticle)
        }
        items(
            items = article.listParts,
            itemContent = { PartItem(it) }
        )
        item { Spacer(Modifier.height(20.dp)) }
    }
}

@Composable
private fun ListTitle(titleArticle: String) {
    Spacer(Modifier.height(20.dp))
    Text(
        text = titleArticle,
        style = Theme.typography.titleArticle
    )
    Spacer(Modifier.height(10.dp))
}

@Composable
private fun PartItem(part: PartArticleResponse) {
    Spacer(Modifier.height(10.dp))
    ViewCardCustomElevation() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = part.partText,
                style = Theme.typography.titlePart
            )
            part.points?.forEach { point ->
                ViewPointItem(
                    pointText = point,
                    backgroundColor = Theme.colors.onBackground
                )
                Spacer(Modifier.height(5.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun PreviewLight() = AppTheme(false) {
    PoliceActScreen(
        state = PoliceActScreenState.getPreview(),
        isVisibleUiBars = true,
        sendEvent = {}
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF080c17)
private fun PreviewDark() = AppTheme {
    PoliceActScreen(
        state = PoliceActScreenState.getPreview(),
        isVisibleUiBars = true,
        sendEvent = {}
    )
}