package com.zlsp.ppsphb.ui.screens.grounds

import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.base.MVI
import com.zlsp.ppsphb.data.repository.grounds.model.GroundsResponse

data class GroundsScreenState(
    override val contentState: ContentState,
    val listGrounds: List<GroundsResponse>
): MVI.State {
    companion object {
        fun getPreview() = GroundsScreenState(
            contentState = ContentState.Content,
            listGrounds = listOf(GroundsResponse.getDemo(1.0), GroundsResponse.getDemo(2.0))
        )

        fun getDefault() = GroundsScreenState(
            contentState = ContentState.Loading,
            listGrounds = emptyList()
        )
    }
}

sealed interface GroundsScreenEvent: MVI.Event{
    object Init: GroundsScreenEvent
}

sealed interface GroundsScreenEffect: MVI.Effect