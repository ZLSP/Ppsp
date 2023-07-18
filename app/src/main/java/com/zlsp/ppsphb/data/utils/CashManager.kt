package com.zlsp.ppsphb.data.utils

import android.util.LruCache
import com.zlsp.ppsphb.data.repository.police_act.models.GetAppDataResponse

class CacheManager {
    private val maxMemory by lazy {
        (Runtime.getRuntime().maxMemory() / 1024).toInt()
    }

    private val cashSize by lazy { maxMemory / 8 }

    private val lruCache = LruCache<String, GetAppDataResponse>(cashSize)

    fun put(key: String, value: GetAppDataResponse) {
        lruCache.put(key, value)
    }

    fun get(key: String): GetAppDataResponse? {
        return lruCache.get(key)
    }

}