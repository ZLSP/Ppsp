package com.zlsp.ppsphb.data.network.ktor

import com.zlsp.ppsphb.data.network.ext.addLogger
import com.zlsp.ppsphb.data.network.ext.setGson
import com.zlsp.ppsphb.data.network.ext.setTimeOut
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

object KtorApiClient {
    val client = HttpClient(OkHttp) {
        expectSuccess = true
        setGson()
        addLogger()
        setTimeOut()
    }
}