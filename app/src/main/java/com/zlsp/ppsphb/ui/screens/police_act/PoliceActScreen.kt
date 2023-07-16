package com.zlsp.ppsphb.ui.screens.police_act

import android.content.Context
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
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zlsp.ppsphb.R
import com.zlsp.ppsphb.ui.theme.AppTheme
import com.zlsp.ppsphb.ui.theme.LocalThemeMode
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun PoliceActScreen(
    state: PoliceActScreenState,
    sendEvent: (PoliceActScreenEvent) -> Unit,
) {
    InitListArticles { sendEvent(PoliceActScreenEvent.Init(it)) }
    Row {
        ListParts(state.activeArticle)
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
            color = Theme.colors.secondary
        )
        ListArticle(
            activeArticle = state.activeArticle,
            listArticle = state.listArticles,
            onClickItem = { sendEvent(PoliceActScreenEvent.OnClickArticle(it)) }
        )
    }
}

@Composable
private fun ListArticle(
    activeArticle: PoliceActArticle?,
    listArticle: List<PoliceActArticle>,
    onClickItem: (PoliceActArticle) -> Unit
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
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(horizontal = 5.dp)
    ) {
        item { Spacer(Modifier.height(10.dp)) }
        items(
            items = listArticle,
            key = { it.id },
        ) {
            val background = if (it == activeArticle) {
                Theme.colors.secondary
            } else {
                Theme.colors.background
            }
            Spacer(Modifier.height(10.dp))
            Card(
                modifier = modifier.clickable { onClickItem(it) },
                backgroundColor = background,
                contentColor = Theme.colors.onBackground,
                elevation = elevation,
                shape = RoundedCornerShape(6.dp),
            ) {
                Box(Modifier.size(50.dp)) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = it.nameItemAct,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600
                        )
                    )
                }
            }
        }
        item { Spacer(Modifier.height(10.dp)) }
    }
}

@Composable
private fun RowScope.ListParts(article: PoliceActArticle?) {
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
                text = article?.titleAct ?: "",
                style = TextStyle(
                    color = Theme.colors.onBackground,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600
                )
            )
            Spacer(Modifier.height(10.dp))
        }
        items(
            items = article?.listParts ?: emptyList()
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
                        text = it,
                        style = TextStyle(
                            color = Theme.colors.onBackground,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400
                        )
                    )
                }
            }
        }
        item { Spacer(Modifier.height(20.dp)) }
    }
}

@Composable
private fun InitListArticles(
    context: Context = LocalContext.current,
    onInit: (List<PoliceActArticle>) -> Unit,
) {
    LaunchedEffect(true) {
        onInit(getListActs(context))
    }
}

private fun getListActs(context: Context): List<PoliceActArticle> {
    val act1 = context.resources.getStringArray(R.array.array_act_1).mapToPoliceAct()
    val act2 = context.resources.getStringArray(R.array.array_act_2).mapToPoliceAct()
    val act3 = context.resources.getStringArray(R.array.array_act_3).mapToPoliceAct()
    val act4 = context.resources.getStringArray(R.array.array_act_4).mapToPoliceAct()
    val act5 = context.resources.getStringArray(R.array.array_act_5).mapToPoliceAct()
    val act6 = context.resources.getStringArray(R.array.array_act_6).mapToPoliceAct()
    val act7 = context.resources.getStringArray(R.array.array_act_7).mapToPoliceAct()
    val act8 = context.resources.getStringArray(R.array.array_act_8).mapToPoliceAct()
    val act9 = context.resources.getStringArray(R.array.array_act_9).mapToPoliceAct()
    val act10 = context.resources.getStringArray(R.array.array_act_10).mapToPoliceAct()
    val act11 = context.resources.getStringArray(R.array.array_act_11).mapToPoliceAct()
    val act12 = context.resources.getStringArray(R.array.array_act_12).mapToPoliceAct()
    val act13 = context.resources.getStringArray(R.array.array_act_13).mapToPoliceAct()
    val act14 = context.resources.getStringArray(R.array.array_act_14).mapToPoliceAct()
    return listOf(
        act1,
        act2,
        act3,
        act4,
        act5,
        act6,
        act7,
        act8,
        act9,
        act10,
        act11,
        act12,
        act13,
        act14,
    )
}

private fun Array<String>.mapToPoliceAct(): PoliceActArticle {
    var id = 0.0
    var name = ""
    var title = ""
    val listParts = mutableListOf<String>()
    this.toList().forEachIndexed { index, s ->
        when (index) {
            0 -> title = s
            this.lastIndex -> {
                name = s
                id = s.toDoubleOrNull() ?: 0.0
            }

            else -> listParts.add(s)
        }
    }
    return PoliceActArticle(
        id = id,
        nameItemAct = name,
        titleAct = title,
        listParts = listParts,
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun PreviewLight() = AppTheme(false) {
    PoliceActScreen(
        state = PoliceActScreenState(
            listArticles = PoliceActArticle.getPreviewList(),
            activeArticle = PoliceActArticle.getPreviewItem(1)
        ),
        sendEvent = {}
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF080c17)
private fun PreviewDark() = AppTheme {
    PoliceActScreen(
        state = PoliceActScreenState(
            listArticles = PoliceActArticle.getPreviewList(),
            activeArticle = PoliceActArticle.getPreviewItem(1)
        ),
        sendEvent = {}
    )
}