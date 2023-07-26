package com.zlsp.ppsphb.ui.screens.police_act

import com.zlsp.ppsphb.base.ContentState
import com.zlsp.ppsphb.base.MVI
import com.zlsp.ppsphb.data.repository.police_act.models.ActArticleResponse

data class PoliceActScreenState(
    override val contentState: ContentState,
    val dateRedaction: String,
    val activeArticle: ActArticleResponse,
    val titleAct: String,
    val listArticles: List<ActArticleResponse>,
    val listArticlesName: List<String>,
) : MVI.State {
    companion object {
        fun getPreview() = PoliceActScreenState(
            contentState = ContentState.Content,
            dateRedaction = "06.02.2023",
            titleAct = "Федеральный закон от 07.02.2011 N 3-ФЗ \"О полиции\"",
            activeArticle = ActArticleResponse.getDemo(1.0),
            listArticlesName = listOf("1", "2"),
            listArticles = listOf(ActArticleResponse.getDemo(1.0), ActArticleResponse.getDemo(2.0)),
        )

        fun getDefault() = PoliceActScreenState(
            contentState = ContentState.Loading,
            dateRedaction = "",
            listArticles = emptyList(),
            listArticlesName = emptyList(),
            activeArticle = ActArticleResponse.getDemo(1.0),
            titleAct = ""
        )
    }
}

sealed interface PoliceActScreenEvent : MVI.Event {
    class OnClickArticle(val article: ActArticleResponse) : PoliceActScreenEvent
    object Init : PoliceActScreenEvent
}

sealed interface PoliceActScreenEffect : MVI.Effect {
    object ShowAd: PoliceActScreenEffect
}