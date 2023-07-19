package com.zlsp.ppsphb.base

sealed interface MVI {
    interface Event: MVI
    interface Effect: MVI
    abstract class State: MVI {
        abstract val contentState: ContentState
    }
}