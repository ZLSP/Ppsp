package com.zlsp.ppsphb.data.repository.main

import com.zlsp.ppsphb.data.repository.police_act.models.GetAppDataResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getAppData(): Flow<GetAppDataResponse?>
}