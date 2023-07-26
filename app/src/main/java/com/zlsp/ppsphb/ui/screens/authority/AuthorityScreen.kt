package com.zlsp.ppsphb.ui.screens.authority

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.zlsp.ppsphb.ui.general.DefaultScreenWrapper
import com.zlsp.ppsphb.ui.general.ViewCardCustomElevation
import com.zlsp.ppsphb.ui.theme.AppTheme
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun AuthorityScreen(
    state: AuthorityScreenState,
    sendEvent: (AuthorityScreenEvent) -> Unit,
) {
    DefaultScreenWrapper(
        contentState = state.contentState,
        onClickButtonError = { sendEvent(AuthorityScreenEvent.Init) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
        ) {
            items(
                items = state.listAuthority,
                key = { it.id }
            ) {
                var isExpand by remember { mutableStateOf(false) }
                Spacer(Modifier.height(10.dp))
                ViewCardCustomElevation(
                    modifier = Modifier.clickable { isExpand = !isExpand },
                    border = BorderStroke(1.dp, Theme.colors.secondary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.title,
                            style = Theme.typography.titleScreen.copy(textAlign = TextAlign.Center),
                            color = Theme.colors.onBackground
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.subTitle,
                            style = Theme.typography.titleArticle.copy(textAlign = TextAlign.Center),
                            color = Theme.colors.onBackground
                        )
                        AnimatedVisibility(
                            visible = isExpand,
                            enter = expandVertically(),
                            exit = shrinkVertically()
                        ) {
                            Column(Modifier.fillMaxWidth()) {
                                it.listArticles.forEach { article ->
                                    Spacer(Modifier.height(10.dp))
                                    Divider(color = Theme.colors.onBackground.copy(0.1f))
                                    Spacer(Modifier.height(10.dp))
                                    Text(
                                        text = article.titleArticle,
                                        style = Theme.typography.titleArticle,
                                        color = Theme.colors.onBackground
                                    )
                                    Spacer(Modifier.height(10.dp))
                                    Divider(color = Theme.colors.onBackground.copy(0.1f))
                                    article.listParts.forEach { part ->
                                        Spacer(Modifier.height(10.dp))
                                        Text(
                                            text = part.partText,
                                            style = Theme.typography.titlePart,
                                            color = Theme.colors.onBackground
                                        )
                                        part.points?.let { points ->
                                            points.forEach { point ->
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
    AuthorityScreen(
        state = AuthorityScreenState.getPreview(),
        sendEvent = {}
    )
}

@Composable
@Preview
private fun PreviewLight() = AppTheme(false) {
    AuthorityScreen(
        state = AuthorityScreenState.getPreview(),
        sendEvent = {}
    )
}