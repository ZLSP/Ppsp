package com.zlsp.ppsphb.base

sealed interface MVI {
    interface Event: MVI
    interface Effect: MVI
    interface State: MVI
}