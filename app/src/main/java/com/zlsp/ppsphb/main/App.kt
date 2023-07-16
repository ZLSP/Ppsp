package com.zlsp.ppsphb.main

import android.app.Application
import com.yandex.mobile.ads.common.MobileAds
import dagger.hilt.android.HiltAndroidApp
import org.orbitmvi.orbit.compose.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {
            Timber.tag("YandexAds").d("Иницилизация успешна")
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}