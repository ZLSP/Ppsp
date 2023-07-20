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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.data.repository.police_act.models.ActArticleResponse
import com.zlsp.ppsphb.ui.general.ErrorLayout
import com.zlsp.ppsphb.ui.general.LoadingLayout
import com.zlsp.ppsphb.ui.theme.AppTheme
import com.zlsp.ppsphb.ui.theme.LocalThemeMode
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun PoliceActScreen(
    state: PoliceActScreenState,
    isVisibleUiBars: Boolean,
    sendEvent: (PoliceActScreenEvent) -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        Row {
            ListParts(state.activeArticle)
            ListArticlesNumber(
                activeArticle = state.activeArticle.id,
                isVisible = isVisibleUiBars,
                listArticle = state.listArticles,
                onClickItem = { sendEvent(PoliceActScreenEvent.OnClickArticle(it)) }
            )
        }
        when (state.contentState) {
            is ContentState.Content -> {}
            is ContentState.Error -> ErrorLayout(
                title = state.contentState.text,
                onClick = { sendEvent(PoliceActScreenEvent.Init) }
            )

            is ContentState.Loading -> LoadingLayout()
        }
    }
}

@Composable
private fun ListArticlesNumber(
    activeArticle: Double,
    isVisible: Boolean,
    listArticle: List<ActArticleResponse>,
    onClickItem: (ActArticleResponse) -> Unit
) {
    val isDarkMode = LocalThemeMode.current.isDarkMode
    val modifier = if (isDarkMode) {
        Modifier.shadow(
            elevation = 25.dp,
            spotColor = Color.White,
            shape = RoundedCornerShape(6.dp)
        )
    } else {
        Modifier
    }
    val elevation = if (isDarkMode) 0.dp else 15.dp
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
            ) {
                val background = if (it.id == activeArticle) {
                    Theme.colors.secondary
                } else {
                    Theme.colors.background
                }
                Spacer(Modifier.height(10.dp))
                Card(
                    modifier = modifier.clickable { onClickItem(it) },
                    backgroundColor = background,
                    elevation = elevation,
                    shape = RoundedCornerShape(6.dp),
                ) {
                    Box(Modifier.size(50.dp)) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = it.numArticle,
                            style = Theme.typography.numArticle
                        )
                    }
                }
            }
            item { Spacer(Modifier.height(10.dp)) }
        }
    }
}

@Composable
private fun RowScope.ListParts(article: ActArticleResponse) {
    val isDarkMode = LocalThemeMode.current.isDarkMode
    val modifier = if (isDarkMode) {
        Modifier.shadow(
            elevation = 20.dp,
            spotColor = Color.White,
            shape = RoundedCornerShape(12.dp)
        )
    } else {
        Modifier
    }
    val elevation = if (isDarkMode) 0.dp else 15.dp
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        item {
            Spacer(Modifier.height(20.dp))
            Text(
                text = article.titleArticle,
                style = Theme.typography.titleArticle
            )
            Spacer(Modifier.height(10.dp))
        }
        items(
            items = article.listParts
        ) {
            Spacer(Modifier.height(10.dp))
            Card(
                modifier = modifier,
                backgroundColor = Theme.colors.background,
                contentColor = Theme.colors.onBackground,
                elevation = elevation,
                shape = RoundedCornerShape(12.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = it.partText,
                        style = Theme.typography.titlePart
                    )
                    it.points?.forEach { point ->
                        Spacer(Modifier.height(5.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = Theme.colors.onBackground.copy(0.1f),
                            shape = RoundedCornerShape(12.dp),
                            elevation = 0.dp,
                            contentColor = Theme.colors.onBackground
                        ) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = point,
                                    style = Theme.typography.textBody
                                )
                            }
                        }
                    }
                }
            }
        }
        item { Spacer(Modifier.height(20.dp)) }
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