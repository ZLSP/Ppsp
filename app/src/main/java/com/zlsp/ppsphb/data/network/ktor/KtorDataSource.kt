package com.zlsp.ppsphb.data.network.ktor

import com.zlsp.ppsphb.data.repository.main.model.GetAppDataResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class KtorDataSource @Inject constructor() {
    suspend fun getAppData(): GetAppDataResponse {
        return KtorApiClient.client.get(urlString = "https://f84c7488-47fc-42f4-adee-664d73679904.mock.pstmn.io").body()
    }
}