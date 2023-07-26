package com.zlsp.ppsphb.ui.screens.materials

import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.base.MVI
import com.zlsp.ppsphb.data.repository.materials.model.MaterialResponse

data class MaterialsScreenState(
    override val contentState: ContentState,
    val listMaterials: List<MaterialResponse>
): MVI.State {
    companion object{
        fun getDefault() = MaterialsScreenState(
            contentState = ContentState.Loading,
            listMaterials = emptyList()
        )

        fun getPreview() = MaterialsScreenState(
            contentState = ContentState.Content,
            listMaterials = listOf(
                MaterialResponse.getDemo(),
                MaterialResponse.getDemo(),
                MaterialResponse.getDemo(),
                MaterialResponse.getDemo()
            )
        )
    }
}

sealed interface MaterialsScreenEvent: MVI.Event {
    object Init: MaterialsScreenEvent
    class OnClickItem(val material: MaterialResponse): MaterialsScreenEvent
    object ShowContent: MaterialsScreenEvent
}

sealed interface MaterialsScreenEffect: MVI.Effect {
    class ShowRewardedAd(val material: MaterialResponse): MaterialsScreenEffect
}