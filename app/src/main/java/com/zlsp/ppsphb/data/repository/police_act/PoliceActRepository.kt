package com.zlsp.ppsphb.data.repository.police_act

import com.zlsp.ppsphb.data.repository.police_act.models.PoliceActResponse
import kotlinx.coroutines.flow.Flow

interface PoliceActRepository {
    suspend fun getPoliceAct(): Flow<PoliceActResponse?>
}