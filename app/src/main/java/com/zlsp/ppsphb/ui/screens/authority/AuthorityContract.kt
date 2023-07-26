package com.zlsp.ppsphb.ui.screens.authority

import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.base.MVI
import com.zlsp.ppsphb.data.repository.police_act.models.ActArticleResponse

data class AuthorityScreenState(
    override val contentState: ContentState,
    val titleContent: String,
    val authorityList: List<ActArticleResponse>
): MVI.State {
    companion object {
        fun getDefault() = AuthorityScreenState(
            contentState = ContentState.Loading,
            titleContent = "",
            authorityList = emptyList()
        )

        fun getPreview() = AuthorityScreenState(
            contentState = ContentState.Content,
            titleContent = "Должностные лица ППСП уполномочены составлять протоколы об АП, предусмотренных КоАП РФ по следующим статьям:",
            authorityList = listOf(
                ActArticleResponse.getDemo(1.0),
                ActArticleResponse.getDemo(2.0),
                ActArticleResponse.getDemo(3.0),
            )
        )
    }
}

sealed interface AuthorityScreenEvent: MVI.Event{
    object Init: AuthorityScreenEvent
}

sealed interface AuthorityScreenEffect: MVI.Effect {
    object ShowAd: AuthorityScreenEffect
}