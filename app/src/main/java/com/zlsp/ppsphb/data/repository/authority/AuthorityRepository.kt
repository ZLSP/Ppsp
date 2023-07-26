package com.zlsp.ppsphb.data.repository.authority

import com.zlsp.ppsphb.data.repository.authority.model.AuthorityResponse
import kotlinx.coroutines.flow.Flow

interface AuthorityRepository {
    suspend fun getAuthority(): Flow<AuthorityResponse>
}