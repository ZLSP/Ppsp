package com.zlsp.ppsphb.data.repository.materials

import com.zlsp.ppsphb.data.repository.main.MainRepository
import com.zlsp.ppsphb.data.repository.materials.model.MaterialResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MaterialsRepositoryImpl @Inject constructor(private val mainRepository: MainRepository) :
    MaterialsRepository {
    override suspend fun getMaterials(): Flow<List<MaterialResponse>> {
        return mainRepository.getAppData()
            .map { it.listMaterial }
    }
}