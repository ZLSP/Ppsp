package com.zlsp.ppsphb.ui.general

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun ViewPointItem(pointText: String) {
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
                text = pointText,
                style = Theme.typography.textBody
            )
        }
    }
}