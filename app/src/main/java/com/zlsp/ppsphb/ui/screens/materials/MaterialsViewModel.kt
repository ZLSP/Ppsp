package com.zlsp.ppsphb.ui.screens.materials

import androidx.lifecycle.viewModelScope
import com.zlsp.ppsphb.base.BaseViewModel
import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.data.repository.materials.MaterialsRepository
import com.zlsp.ppsphb.data.repository.materials.model.MaterialResponse
import com.zlsp.ppsphb.data.utils.FBAnalyticsUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MaterialsViewModel @Inject constructor(private val materialsRepository: MaterialsRepository) :
    BaseViewModel<MaterialsScreenState, MaterialsScreenEffect, MaterialsScreenEvent>() {
    override val containerHost: Container<MaterialsScreenState, MaterialsScreenEffect> =
        container(MaterialsScreenState.getDefault()) {
            init()
        }

    override fun sendEvent(event: MaterialsScreenEvent) {
        when (event) {
            MaterialsScreenEvent.Init -> init()
            is MaterialsScreenEvent.OnClickItem -> showAd(event.material)
            MaterialsScreenEvent.ShowContent -> showContent()
        }
    }

    private fun showContent() = intent {
        reduce { state.copy(contentState = ContentState.Content) }
    }

    private fun showAd(material: MaterialResponse) = intent {
        FBAnalyticsUtils.logEvent(FBAnalyticsUtils.LOG_ON_CLICK_DOWNLOAD)
        reduce { state.copy(contentState = ContentState.Loading) }
        postSideEffect(MaterialsScreenEffect.ShowRewardedAd(material))
    }

    private fun init() = intent {
        reduce { state.copy(contentState = ContentState.Loading) }
        materialsRepository.getMaterials()
            .onEach {
                reduce { state.copy(contentState = ContentState.Content, listMaterials = it) }
            }
            .launchIn(viewModelScope)
    }
}