package com.zlsp.ppsphb.main

import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.base.MVI
import com.zlsp.ppsphb.ui.theme.models.TypographySize

sealed interface MainActivityEffect: MVI.Effect

sealed interface MainActivityEvent: MVI.Event {
    object OnClickThemeMode: MainActivityEvent
    object OnClickTypoSize: MainActivityEvent
}

data class MainActivityState(
    override val contentState: ContentState = ContentState.Content,
    val isDarkMode: Boolean,
    val typoSize: TypographySize,
): MVI.State