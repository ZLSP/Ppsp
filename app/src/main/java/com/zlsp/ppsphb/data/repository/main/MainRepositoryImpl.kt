package com.zlsp.ppsphb.data.repository.main

import com.zlsp.ppsphb.data.network.ktor.KtorDataSource
import com.zlsp.ppsphb.data.repository.police_act.models.GetAppDataResponse
import com.zlsp.ppsphb.data.utils.AssetJsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dataSource: KtorDataSource,
    private val assetJsonReader: AssetJsonReader
) : MainRepository {
    override fun getAppData(): Flow<GetAppDataResponse?> {
        return flow { emit(dataSource.getAppData()) }
            .catch {
                emit(assetJsonReader.getLocalAppData())
            }.flowOn(Dispatchers.IO)

    }
}