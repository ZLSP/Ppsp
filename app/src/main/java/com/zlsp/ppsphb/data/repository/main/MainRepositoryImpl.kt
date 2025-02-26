package com.zlsp.ppsphb.data.repository.main

import android.content.SharedPreferences
import com.google.gson.Gson
import com.zlsp.ppsphb.data.network.ktor.KtorDataSource
import com.zlsp.ppsphb.data.repository.main.model.GetAppDataResponse
import com.zlsp.ppsphb.data.utils.CacheManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val KEY_APP_DATA_CACHE = "KEY_APP_DATA_CACHE"

class MainRepositoryImpl @Inject constructor(
    private val dataSource: KtorDataSource,
    private val prefs: SharedPreferences,
) : MainRepository {

    private val cacheManager = CacheManager()

    override fun getAppData(): Flow<GetAppDataResponse> {
        val cache = cacheManager.get()
        return cache?.let { flowOf(it) } ?: flow { emit(dataSource.getAppData()) }
            .map {
                Gson().fromJson(it, GetAppDataResponse::class.java)
                putPrefsCache(it)
                it
            }.catch {
                val longCache = getPrefsCache() ?: throw ClearPrefsException()
                emit(longCache)
            }.map {
                Gson().fromJson(it, GetAppDataResponse::class.java)
            }.onEach { appData ->
                cacheManager.put(appData)
            }.flowOn(Dispatchers.IO)
    }

    private fun getPrefsCache(): String? {
        return prefs.getString(KEY_APP_DATA_CACHE, null)
    }

    private fun putPrefsCache(dataText: String) {
        prefs.edit().putString(KEY_APP_DATA_CACHE, dataText).apply()
    }
}

class ClearPrefsException : RuntimeException("Ошибка загрузки данных, попробуйте вернуться позже!")