package com.zlsp.ppsphb.ui.screens.police_act

import com.zlsp.ppsphb.base.MVI

data class PoliceActScreenState(
    val listArticles: List<Double>,
    val activeArticle: Double,
    val listParts: List<Int>
): MVI.State

sealed interface PoliceActScreenEvent: MVI.Event {
    class OnClickArticle(val article: Int): PoliceActScreenEvent
}

sealed interface PoliceActScreenEffect: MVI.Effect