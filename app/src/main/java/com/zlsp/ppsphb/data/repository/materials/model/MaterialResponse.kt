package com.zlsp.ppsphb.data.repository.materials.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MaterialResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String
) {
    companion object {
        fun getDemo() = MaterialResponse(
            title = "Рапорт об АП",
            link = ""
        )
    }
}
