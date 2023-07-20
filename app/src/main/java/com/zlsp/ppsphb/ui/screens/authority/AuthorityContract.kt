package com.zlsp.ppsphb.ui.screens.authority

import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.base.MVI

data class AuthorityScreenState(
    override val contentState: ContentState = ContentState.Loading,

): MVI.State()