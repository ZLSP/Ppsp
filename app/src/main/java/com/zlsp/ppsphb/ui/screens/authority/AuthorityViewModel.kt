package com.zlsp.ppsphb.ui.screens.authority

import androidx.lifecycle.viewModelScope
import com.zlsp.ppsphb.base.BaseViewModel
import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.data.repository.authority.AuthorityRepository
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
class AuthorityViewModel @Inject constructor(
    private val authorityRepository: AuthorityRepository,
    private val yandexAdRepository: YandexAdRepository
) :
    BaseViewModel<AuthorityScreenState, AuthorityScreenEffect, AuthorityScreenEvent>() {
    override val containerHost: Container<AuthorityScreenState, AuthorityScreenEffect> = container(
        AuthorityScreenState.getDefault()
    ) {
        init()
    }

    override fun sendEvent(event: AuthorityScreenEvent) {
        when (event) {
            AuthorityScreenEvent.Init -> init()
            AuthorityScreenEvent.OnClickItem -> showAd()
        }
    }

    private fun showAd() = intent {
        if (yandexAdRepository.checkIsShowAd()) {
            postSideEffect(AuthorityScreenEffect.ShowAd)
        }
    }

    private fun init() = intent {
        reduce { state.copy(contentState = ContentState.Loading) }
        authorityRepository.getAuthority()
            .onEach {
                reduce {
                    state.copy(
                        contentState = ContentState.Content,
                        titleContent = it.title,
                        authorityList = it.listAuthority
                    )
                }
            }.launchIn(viewModelScope)
    }

}