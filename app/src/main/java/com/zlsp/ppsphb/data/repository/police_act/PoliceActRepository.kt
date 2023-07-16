package com.zlsp.ppsphb.data.repository.police_act

import com.zlsp.ppsphb.data.repository.police_act.models.PoliceArticle

interface PoliceActRepository {
    fun getListArticles(): List<PoliceArticle>
    fun getArticle(id: Double): PoliceArticle
}