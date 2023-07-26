package com.zlsp.ppsphb.ui.screens.grounds

import androidx.lifecycle.viewModelScope
import com.zlsp.ppsphb.base.BaseViewModel
import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.data.repository.grounds.GroundsRepository
import com.zlsp.ppsphb.data.repository.yandex.YandexAdRepository
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
class GroundsViewModel @Inject constructor(
    private val groundsRepository: GroundsRepository,
    private val yandexAdRepository: YandexAdRepository
) :
    BaseViewModel<GroundsScreenState, GroundsScreenEffect, GroundsScreenEvent>() {
    override val containerHost: Container<GroundsScreenState, GroundsScreenEffect> =
        container(GroundsScreenState.getDefault()) {
            init()
        }

    override fun sendEvent(event: GroundsScreenEvent) {
        when (event) {
            GroundsScreenEvent.Init -> init()
            GroundsScreenEvent.OnClickItem -> showAd()
        }
    }

    private fun showAd() = intent {
        if (yandexAdRepository.checkIsShowAd()) {
            postSideEffect(GroundsScreenEffect.ShowAd)
        }
    }

    private fun init() = intent {
        reduce { state.copy(contentState = ContentState.Loading) }
        groundsRepository.getListGrounds()
            .onEach {
                val newState = if (it.isEmpty()) {
                    state.copy(contentState = ContentState.Error("Что-то пошло не так!"))
                } else {
                    state.copy(
                        contentState = ContentState.Content,
                        listGrounds = it
                    )
                }
                reduce { newState }
            }.launchIn(viewModelScope)
    }
}