package com.zlsp.ppsphb.ui.screens.police_act

import androidx.lifecycle.viewModelScope
import com.zlsp.ppsphb.base.BaseViewModel
import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.data.repository.police_act.PoliceActRepository
import com.zlsp.ppsphb.data.repository.police_act.models.ActArticleResponse
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
class PoliceActViewModel @Inject constructor(
    private val policeActRepository: PoliceActRepository,
    private val yandexAdRepository: YandexAdRepository
) :
    BaseViewModel<PoliceActScreenState, PoliceActScreenEffect, PoliceActScreenEvent>() {
    override val containerHost: Container<PoliceActScreenState, PoliceActScreenEffect> = container(
        PoliceActScreenState.getDefault()
    ) {
        init()
    }

    override fun sendEvent(event: PoliceActScreenEvent) {
        when (event) {
            is PoliceActScreenEvent.OnClickArticle -> onClickArticle(event.article)
            is PoliceActScreenEvent.Init -> init()
        }
    }

    private fun init() = intent {
        reduce { state.copy(contentState = ContentState.Loading) }
        policeActRepository.getPoliceAct()
            .onEach { policeAct ->
                val newState = if (policeAct == null) {
                    state.copy(contentState = ContentState.Error("Что-то пошло не так!"))
                } else {
                    state.copy(
                        contentState = ContentState.Content,
                        dateRedaction = policeAct.dateRedaction,
                        activeArticle = policeAct.listArticles.first(),
                        titleAct = policeAct.title,
                        listArticlesName = policeAct.listArticles.map { it.numArticle },
                        listArticles = policeAct.listArticles
                    )
                }
                reduce { newState }
            }
            .launchIn(viewModelScope)
    }

    private fun onClickArticle(article: ActArticleResponse) = intent {
        reduce { state.copy(activeArticle = article) }
        if (yandexAdRepository.checkIsShowAd()) {
            postSideEffect(PoliceActScreenEffect.ShowAd)
        }
    }
}