package com.zlsp.ppsphb.ui.screens.police_act

import com.zlsp.ppsphb.base.MVI

data class PoliceActScreenState(
    val listArticles: List<PoliceActArticle>,
    val activeArticle: PoliceActArticle?
): MVI.State

sealed interface PoliceActScreenEvent: MVI.Event {
    class OnClickArticle(val article: PoliceActArticle): PoliceActScreenEvent
    class Init(val listArticles: List<PoliceActArticle>): PoliceActScreenEvent
}

sealed interface PoliceActScreenEffect: MVI.Effect

data class PoliceActArticle(
    val id: Double,
    val nameItemAct: String,
    val titleAct: String,
    val listParts: List<String>
) {
    companion object{
        fun getPreviewItem(id: Int): PoliceActArticle = PoliceActArticle(
            id = id.toDouble(),
            nameItemAct = id.toString(),
            titleAct = "Статья $id",
            listParts = listOf(
                "1. Полиция предназначена для защиты жизни, здоровья, прав и свобод граждан Российской Федерации, иностранных граждан, лиц без гражданства (далее также - граждане; лица), для противодействия преступности, охраны общественного порядка, собственности и для обеспечения общественной безопасности.",
                "1. Полиция предназначена для защиты жизни, здоровья, прав и свобод граждан Российской Федерации, иностранных граждан, лиц без гражданства (далее также - граждане; лица), для противодействия преступности, охраны общественного порядка, собственности и для обеспечения общественной безопасности.",
                "1. Полиция предназначена для защиты жизни, здоровья, прав и свобод граждан Российской Федерации, иностранных граждан, лиц без гражданства (далее также - граждане; лица), для противодействия преступности, охраны общественного порядка, собственности и для обеспечения общественной безопасности.",
                "1. Полиция предназначена для защиты жизни, здоровья, прав и свобод граждан Российской Федерации, иностранных граждан, лиц без гражданства (далее также - граждане; лица), для противодействия преступности, охраны общественного порядка, собственности и для обеспечения общественной безопасности.",
                "1. Полиция предназначена для защиты жизни, здоровья, прав и свобод граждан Российской Федерации, иностранных граждан, лиц без гражданства (далее также - граждане; лица), для противодействия преступности, охраны общественного порядка, собственности и для обеспечения общественной безопасности.",
            )
        )

        fun getPreviewList() : List<PoliceActArticle> = listOf(
            getPreviewItem(1),
            getPreviewItem(2),
            getPreviewItem(3),
            getPreviewItem(4),
            getPreviewItem(5),
            getPreviewItem(6),
        )
    }
}