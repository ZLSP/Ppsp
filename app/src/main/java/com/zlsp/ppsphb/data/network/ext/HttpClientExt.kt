package com.zlsp.ppsphb.data.network.ext

import com.zlsp.ppsphb.BuildConfig
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import timber.log.Timber

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.setGson() {
    install(ContentNegotiation) { gson() }
}

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.addLogger(isMock: Boolean = false) {
    if (BuildConfig.DEBUG) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    val msg = if (isMock) {
                        "========KTOR-MOCK-HttpClient========\n$message"
                    } else {
                        "========KTOR-DEFAULT-HttpClient======\n$message"
                    }
                    Timber.tag("Timber").d(msg)
                }
            }
            level = LogLevel.ALL
        }
    }
}

fun HttpClientConfig<OkHttpConfig>.setTimeOut() {
    install(HttpTimeout) {
        connectTimeoutMillis = 15000
        socketTimeoutMillis = 60000
    }
}
