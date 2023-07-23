package com.zlsp.ppsphb.ui.screens.authority

import androidx.lifecycle.viewModelScope
import com.zlsp.ppsphb.base.BaseViewModel
import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.data.repository.authority.AuthorityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AuthorityViewModel @Inject constructor(private val authorityRepository: AuthorityRepository) :
    BaseViewModel<AuthorityScreenState, AuthorityScreenEffect, AuthorityScreenEvent>() {
    override val containerHost: Container<AuthorityScreenState, AuthorityScreenEffect> =
        container(AuthorityScreenState.getDefault()) {
            init()
        }

    override fun sendEvent(event: AuthorityScreenEvent) {

    }

    private fun init() = intent {
        reduce { state.copy(contentState = ContentState.Loading) }
        authorityRepository.getListAuthority()
            .onEach {
                val newState = if (it.isEmpty()) {
                    state.copy(contentState = ContentState.Error("Что-то пошло не так!"))
                } else {
                    state.copy(
                        contentState = ContentState.Content,
                        listAuthority = it
                    )
                }
                reduce { newState }
            }.launchIn(viewModelScope)
    }
}