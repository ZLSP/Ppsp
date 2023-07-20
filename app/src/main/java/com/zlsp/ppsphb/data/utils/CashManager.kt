package com.zlsp.ppsphb.data.utils

import android.util.LruCache
import com.zlsp.ppsphb.data.repository.police_act.models.GetAppDataResponse

class CacheManager {

    companion object {
        private const val KEY_APP_DATA = "KEY_APP_DATA"
    }

    private val maxMemory by lazy {
        (Runtime.getRuntime().maxMemory() / 1024).toInt()
    }

    private val cashSize by lazy { maxMemory / 8 }

    private val lruCache = LruCache<String, GetAppDataResponse>(cashSize)

    fun put(value: GetAppDataResponse) {
        lruCache.put(KEY_APP_DATA, value)
    }

    fun get(): GetAppDataResponse? {
        return lruCache.get(KEY_APP_DATA)
    }

}