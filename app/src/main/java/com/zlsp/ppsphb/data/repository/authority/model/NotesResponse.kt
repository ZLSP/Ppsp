package com.zlsp.ppsphb.data.repository.authority.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NotesResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("listNotes")
    val listNotes: List<String>
)
