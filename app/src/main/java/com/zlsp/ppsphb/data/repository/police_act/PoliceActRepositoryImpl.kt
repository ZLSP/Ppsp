package com.zlsp.ppsphb.data.repository.police_act

import com.zlsp.ppsphb.data.repository.main.MainRepository
import com.zlsp.ppsphb.data.repository.police_act.models.PoliceActResponse
import com.zlsp.ppsphb.data.utils.UserStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PoliceActRepositoryImpl @Inject constructor(
    private val mainRepository: MainRepository,
    private val userStorage: UserStorage
): PoliceActRepository {
    override suspend fun getPoliceAct(): Flow<PoliceActResponse> {
        return mainRepository.getAppData().map {
            it.policeActResponse
        }
    }

    override fun checkIsShowAd(): Boolean = userStorage.checkIsShowAd()
}