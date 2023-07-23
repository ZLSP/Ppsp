package com.zlsp.ppsphb.data.repository.police_act.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PartArticleResponse(
    @SerializedName("partText")
    val partText: String,
    @SerializedName("points")
    val points: List<String>?
) {
    companion object {
        fun getDemo() = PartArticleResponse(
            partText = "1. Деятельность полиции осуществляется по следующим основным направлениям:",
            points = listOf(
                "1) защита личности, общества, государства от противоправных посягательств;",
                "2) предупреждение и пресечение преступлений и административных правонарушений;"
            )
        )
    }
}