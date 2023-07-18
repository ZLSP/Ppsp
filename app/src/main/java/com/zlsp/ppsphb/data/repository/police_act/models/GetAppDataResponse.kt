package com.zlsp.ppsphb.data.repository.police_act.models
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName


@Keep
data class GetAppDataResponse(
    @SerializedName("policeAct")
    val policeActResponse: PoliceActResponse
)

@Keep
data class PoliceActResponse(
    @SerializedName("dateRedaction")
    val dateRedaction: String,
    @SerializedName("listArticles")
    val listArticles: List<ActArticleResponse>,
    @SerializedName("title")
    val title: String
)

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
)

@Keep
data class PartArticleResponse(
    @SerializedName("partText")
    val partText: String,
    @SerializedName("points")
    val points: List<String>?
)