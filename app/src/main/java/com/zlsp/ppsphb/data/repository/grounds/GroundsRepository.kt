package com.zlsp.ppsphb.data.repository.grounds

import com.zlsp.ppsphb.data.repository.grounds.model.GroundsResponse
import kotlinx.coroutines.flow.Flow

interface GroundsRepository {
    suspend fun getListGrounds(): Flow<List<GroundsResponse>>
}