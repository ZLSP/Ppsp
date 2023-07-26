package com.zlsp.ppsphb.data.repository.authority.model

import com.google.gson.annotations.SerializedName
import com.zlsp.ppsphb.data.repository.police_act.models.ActArticleResponse

data class AuthorityResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("listArticles")
    val listAuthority: List<ActArticleResponse>
)