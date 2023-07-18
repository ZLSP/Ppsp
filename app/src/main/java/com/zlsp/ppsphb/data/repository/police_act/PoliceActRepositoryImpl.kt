package com.zlsp.ppsphb.data.repository.police_act

import com.zlsp.ppsphb.data.repository.main.MainRepository
import com.zlsp.ppsphb.data.repository.police_act.models.PoliceActResponse
import com.zlsp.ppsphb.data.repository.police_act.models.PoliceArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PoliceActRepositoryImpl @Inject constructor(
    private val mainRepository: MainRepository
): PoliceActRepository {
    override suspend fun getPoliceAct(): Flow<PoliceActResponse?> {
        return mainRepository.getAppData().map {
            it?.policeActResponse
        }
    }

    override fun getListArticles(): List<PoliceArticle> {
        TODO("Not yet implemented")
    }

    override fun getArticle(id: Double): PoliceArticle {
        TODO("Not yet implemented")
    }


}