package com.zlsp.ppsphb.data.repository.grounds.model
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName
import com.zlsp.ppsphb.data.repository.police_act.models.ActArticleResponse

@Keep
data class GroundsResponse(
    @SerializedName("id")
    val id: Double,
    @SerializedName("listArticles")
    val listArticles: List<ActArticleResponse>,
    @SerializedName("subTitle")
    val subTitle: String,
    @SerializedName("title")
    val title: String
) {
    companion object {
        fun getDemo(id: Double) = GroundsResponse(
            id = id,
            listArticles = listOf(ActArticleResponse.getDemo(1.0), ActArticleResponse.getDemo(2.0)),
            subTitle = "(п.2 ч.1 ст.13; п.2 ч.3 ст.28)",
            title = "Проверка документов"
        )
    }
}