package com.zlsp.ppsphb.data.repository.police_act.models

data class PolicePart(
    val partText: String,
    val points: List<String> = emptyList()
)
