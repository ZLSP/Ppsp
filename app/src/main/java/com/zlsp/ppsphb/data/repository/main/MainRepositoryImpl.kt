package com.zlsp.ppsphb.data.repository.main

import com.zlsp.ppsphb.data.network.ktor.KtorDataSource
import com.zlsp.ppsphb.data.repository.main.model.GetAppDataResponse
import com.zlsp.ppsphb.data.utils.AssetJsonReader
import com.zlsp.ppsphb.data.utils.CacheManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dataSource: KtorDataSource,
    private val assetJsonReader: AssetJsonReader
) : MainRepository {

    private val cacheManager = CacheManager()

    override fun getAppData(): Flow<GetAppDataResponse> {
        val cache = cacheManager.get()
        return flow { cache ?: emit(assetJsonReader.getLocalAppData()) }
            .onEach { appData ->
                cache?.let { cacheManager.put(appData) }
            }.flowOn(Dispatchers.IO)

    }
}