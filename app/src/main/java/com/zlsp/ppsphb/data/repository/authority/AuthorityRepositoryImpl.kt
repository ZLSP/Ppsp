package com.zlsp.ppsphb.data.repository.authority

import com.zlsp.ppsphb.data.repository.authority.model.AuthorityResponse
import com.zlsp.ppsphb.data.repository.main.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthorityRepositoryImpl @Inject constructor(private val mainRepository: MainRepository) :
    AuthorityRepository {
    override suspend fun getListAuthority(): Flow<List<AuthorityResponse>> {
        return mainRepository.getAppData().map {
            it?.listAuthority?: emptyList()
        }
    }
}