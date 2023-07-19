package com.zlsp.ppsphb.ui.general

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.R
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun BoxScope.ErrorLayout(
    title: String,
    onClick: () -> Unit
) {
    BoxHideContent {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.img_error),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = title,
                style = Theme.typography.titlePart,
                color = Theme.colors.onBackground
            )
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = onClick,
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Theme.colors.onBackground,
                    contentColor = Theme.colors.background
                ),
                contentPadding = PaddingValues(horizontal = 17.6.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = "Повторить запрос",
                    style = Theme.typography.button
                )
            }
        }
    }
}