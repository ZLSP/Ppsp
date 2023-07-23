package com.zlsp.ppsphb.data.repository.police_act.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ActArticleResponse(
    @SerializedName("id")
    val id: Double,
    @SerializedName("listParts")
    val listParts: List<PartArticleResponse>,
    @SerializedName("numArticle")
    val numArticle: String,
    @SerializedName("titleArticle")
    val titleArticle: String
) {
    companion object {
        fun getDemo(id: Double) = ActArticleResponse(
            id = id,
            listParts = listOf(PartArticleResponse.getDemo(), PartArticleResponse.getDemo()),
            numArticle = "${id.toInt()}",
            titleArticle = "Статья 2. Основные направления деятельности полиции"
        )
    }
}