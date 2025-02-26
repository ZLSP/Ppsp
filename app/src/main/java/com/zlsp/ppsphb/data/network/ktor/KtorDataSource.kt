package com.zlsp.ppsphb.data.network.ktor

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class KtorDataSource @Inject constructor() {
    suspend fun getAppData(): String {
        return KtorApiClient.client.get(
            urlString = "https://drive.google.com/uc?export=download&id=1dtyt7mdv6EP5X-4bA_t7M0aBBUo1rgnu"
        ).bodyAsText()
    }
}