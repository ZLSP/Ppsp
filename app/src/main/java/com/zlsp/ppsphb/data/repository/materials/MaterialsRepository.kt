package com.zlsp.ppsphb.data.repository.materials

import com.zlsp.ppsphb.data.repository.materials.model.MaterialResponse
import kotlinx.coroutines.flow.Flow

interface MaterialsRepository {
    suspend fun getMaterials(): Flow<List<MaterialResponse>>
}