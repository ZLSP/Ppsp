package com.zlsp.ppsphb.data.repository.police_act

import com.zlsp.ppsphb.data.repository.police_act.models.PoliceActResponse
import com.zlsp.ppsphb.data.repository.police_act.models.PoliceArticle
import kotlinx.coroutines.flow.Flow

interface PoliceActRepository {
    suspend fun getPoliceAct() : Flow<PoliceActResponse?>
    fun getListArticles(): List<PoliceArticle>
    fun getArticle(id: Double): PoliceArticle
}