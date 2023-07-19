package com.zlsp.ppsphb.base

sealed class ContentState {
    object Content: ContentState()
    object Loading: ContentState()
    class Error(val text: String): ContentState()
}