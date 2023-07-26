package com.zlsp.ppsphb.data.repository.grounds

import com.zlsp.ppsphb.data.repository.grounds.model.GroundsResponse
import com.zlsp.ppsphb.data.repository.main.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GroundsRepositoryImpl @Inject constructor(private val mainRepository: MainRepository) :
    GroundsRepository {
    override suspend fun getListGrounds(): Flow<List<GroundsResponse>> {
        return mainRepository.getAppData().map {
            it.listGrounds
        }
    }
}