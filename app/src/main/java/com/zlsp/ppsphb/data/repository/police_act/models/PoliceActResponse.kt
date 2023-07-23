package com.zlsp.ppsphb.data.repository.police_act.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PoliceActResponse(
    @SerializedName("dateRedaction")
    val dateRedaction: String,
    @SerializedName("listArticles")
    val listArticles: List<ActArticleResponse>,
    @SerializedName("title")
    val title: String
)