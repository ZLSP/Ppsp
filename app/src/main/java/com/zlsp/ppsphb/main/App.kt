package com.zlsp.ppsphb.main

import android.app.Application
import com.zlsp.ppsphb.data.utils.YandexAdsUtils
import dagger.hilt.android.HiltAndroidApp
import org.orbitmvi.orbit.compose.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        YandexAdsUtils.initYandex(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}