package com.zlsp.ppsphb.data.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zlsp.ppsphb.data.repository.police_act.models.GetAppDataResponse
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.reflect.Type
import javax.inject.Inject

class AssetJsonReader @Inject constructor(context: Context) {
    private val assetManager = context.assets

    private fun readJsonFromAsset(inputStream: InputStream): GetAppDataResponse {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val personListType: Type = object : TypeToken<GetAppDataResponse>() {}.type
        return Gson().fromJson(reader, personListType)
    }

    fun getLocalAppData(): GetAppDataResponse {
        val inputStream: InputStream = assetManager.open("app_data.json")
        return readJsonFromAsset(inputStream)
    }
}