package com.zlsp.ppsphb.ui.screens.materials

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlsp.ppsphb.data.repository.materials.model.MaterialResponse
import com.zlsp.ppsphb.ui.general.DefaultScreenWrapper
import com.zlsp.ppsphb.ui.general.ViewCardCustomElevation
import com.zlsp.ppsphb.ui.theme.AppTheme
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun MaterialsScreen(
    state: MaterialsScreenState,
    sendEvent: (MaterialsScreenEvent) -> Unit
) {
    DefaultScreenWrapper(
        contentState = state.contentState,
        onClickButtonError = { sendEvent(MaterialsScreenEvent.Init) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
        ) {
            items(
                items = state.listMaterials,
                itemContent = {
                    MaterialItem(
                        material = it,
                        onClick = { sendEvent(MaterialsScreenEvent.OnClickItem(it)) }
                    )
                }
            )
        }
    }
}

@Composable
private fun MaterialItem(
    material: MaterialResponse,
    onClick: () -> Unit
) {
    Spacer(Modifier.height(10.dp))
    ViewCardCustomElevation(
        Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = material.title,
                style = Theme.typography.titleArticle,
                color = Theme.colors.onBackground
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun PreviewDark() = AppTheme {
    MaterialsScreen(
        state = MaterialsScreenState.getPreview(),
        sendEvent = {}
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun PreviewLight() = AppTheme(false) {
    MaterialsScreen(
        state = MaterialsScreenState.getPreview(),
        sendEvent = {}
    )
}