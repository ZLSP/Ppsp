package com.zlsp.ppsphb.ui.general

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.data.repository.police_act.models.PartArticleResponse
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun ViewPartItem(part: PartArticleResponse) {
    Spacer(Modifier.height(10.dp))
    Text(
        text = part.partText,
        style = Theme.typography.titlePart,
        color = Theme.colors.onBackground
    )
}