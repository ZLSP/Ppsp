package com.zlsp.ppsphb.ui.screens.police_act

import androidx.lifecycle.viewModelScope
import com.zlsp.ppsphb.base.BaseViewModel
import com.zlsp.ppsphb.data.repository.police_act.PoliceActRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PoliceActViewModel @Inject constructor(private val policeActRepository: PoliceActRepository) :
    BaseViewModel<PoliceActScreenState, PoliceActScreenEffect, PoliceActScreenEvent>() {
    override val containerHost: Container<PoliceActScreenState, PoliceActScreenEffect> = container(
        PoliceActScreenState(
            emptyList(),
            null,
        )
    ) {
        init()
    }

    override fun sendEvent(event: PoliceActScreenEvent) {
        when (event) {
            is PoliceActScreenEvent.Init -> init(event.listArticles)
            is PoliceActScreenEvent.OnClickArticle -> onClickArticle(event.article)
        }
    }

    private fun init() = intent {
        policeActRepository.getPoliceAct()
            .onEach {
                println(it)
            }
            .launchIn(viewModelScope)
    }

    private fun onClickArticle(article: PoliceActArticle) = intent {
        reduce { state.copy(activeArticle = article) }
    }

    private fun init(policeActs: List<PoliceActArticle>) = intent {
        reduce {
            state.copy(
                listArticles = policeActs,
                activeArticle = policeActs.first()
            )
        }
    }
}