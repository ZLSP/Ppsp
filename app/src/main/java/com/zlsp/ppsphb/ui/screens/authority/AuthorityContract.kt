package com.zlsp.ppsphb.ui.screens.authority

import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.base.MVI
import com.zlsp.ppsphb.data.repository.authority.model.AuthorityResponse

data class AuthorityScreenState(
    override val contentState: ContentState,
    val listAuthority: List<AuthorityResponse>
): MVI.State {
    companion object {
        fun getPreview() = AuthorityScreenState(
            contentState = ContentState.Content,
            listAuthority = listOf(AuthorityResponse.getDemo(1.0), AuthorityResponse.getDemo(2.0))
        )

        fun getDefault() = AuthorityScreenState(
            contentState = ContentState.Loading,
            listAuthority = emptyList()
        )
    }
}

sealed interface AuthorityScreenEvent: MVI.Event{
    class OnClickItem(val authorityItem: AuthorityResponse): AuthorityScreenEvent
    object Init: AuthorityScreenEvent
}

sealed interface AuthorityScreenEffect: MVI.Effect