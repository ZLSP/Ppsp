package com.zlsp.ppsphb.data.utils

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

object FBAnalyticsUtils {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    const val LOG_SHOW_AD_REWARDED = "show_ad_rewarded"
    const val LOG_SHOW_AD_INTERSTITIAL = "show_ad_interstitial"
    const val LOG_ON_CLICK_DOWNLOAD = "click_download"
    const val LOG_ON_CLICK_FULL_SCREEN = "click_full_screen"

    fun initFB() {
        firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN) {}
    }

    fun logEvent(log: String) {
        firebaseAnalytics.logEvent(log) {}
    }
}