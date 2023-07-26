package com.zlsp.ppsphb.ui.general

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.data.repository.police_act.models.ActArticleResponse
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun ViewArticleItem(article: ActArticleResponse) {
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
}