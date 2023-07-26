package com.zlsp.ppsphb.data.utils

import android.content.Context
import android.widget.Toast
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds
import com.yandex.mobile.ads.interstitial.InterstitialAd
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener
import com.yandex.mobile.ads.rewarded.Reward
import com.yandex.mobile.ads.rewarded.RewardedAd
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener
import timber.log.Timber

object YandexAdsUtils {
    private const val YANDEX_MOBILE_ADS_TAG = "YandexMobileAds"

    private const val YANDEX_BANNER_TAG = "YandexBanner"
    private const val YANDEX_BANNER_ID = "R-M-1579265-1"

    private const val YANDEX_INTERSTITIAL_TAG = "YandexInterstitial"
    private const val YANDEX_INTERSTITIAL_ID = "R-M-1579265-3"
    private const val yandex_interstitial_id_demo = "R-M-DEMO-320x480"

    private const val YANDEX_REWARDED_TAG = "YandexRewarded"
    private const val YANDEX_REWARDED_ID = "R-M-1579265-4"
    private const val YANDEX_REWARDED_ID_DEMO = "R-M-DEMO-rewarded-client-side-rtb"

    private val adRequest = AdRequest.Builder().build()
    private lateinit var interstitialAd: InterstitialAd
    private lateinit var rewardedAd: RewardedAd

    fun initYandex(ctx: Context) {
        MobileAds.initialize(ctx) {
            Timber.tag(YANDEX_MOBILE_ADS_TAG).d("SDK initialized")
        }
    }

    fun initBanner(ctx: Context, banner: BannerAdView, width: Int) {
        bannerListener(banner)
        banner.apply {
            setAdSize(AdSize.stickySize(ctx, width))
            setAdUnitId(YANDEX_BANNER_ID)
            loadAd(adRequest)
        }
    }
    private fun bannerListener(banner: BannerAdView) {
        banner.setBannerAdEventListener(object : BannerAdEventListener {
            override fun onAdLoaded() {
                Timber.tag(YANDEX_BANNER_TAG).d("onAdLoaded")
            }

            override fun onAdFailedToLoad(p0: AdRequestError) {
                Timber.tag(YANDEX_BANNER_TAG).d("onAdFailedToLoad")
            }

            override fun onAdClicked() {
                Timber.tag(YANDEX_BANNER_TAG).d("onAdClicked")
            }

            override fun onLeftApplication() {
                Timber.tag(YANDEX_BANNER_TAG).d("onLeftApplication")
            }

            override fun onReturnedToApplication() {
                Timber.tag(YANDEX_BANNER_TAG).d("onReturnedToApplication")
            }

            override fun onImpression(p0: ImpressionData?) {
                Timber.tag(YANDEX_BANNER_TAG).d("onImpression: ${p0.toString()}")
            }

        })
    }

    fun showInterstitial(ctx: Context) {
        interstitialAd = InterstitialAd(ctx)
        interstitialAd.setAdUnitId(YANDEX_INTERSTITIAL_ID)
        interstitialAd.setInterstitialAdEventListener(object : InterstitialAdEventListener {
            override fun onAdLoaded() {
                Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdLoaded")
                interstitialAd.show()
            }

            override fun onAdFailedToLoad(p0: AdRequestError) {
                Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdFailedToLoad: ${p0.description}")
            }

            override fun onAdShown() {
                Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdShown")
            }

            override fun onAdDismissed() {
                Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdDismissed")
            }

            override fun onAdClicked() {
                Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdClicked")
            }

            override fun onLeftApplication() {
                Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onLeftApplication")
            }

            override fun onReturnedToApplication() {
                Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onReturnedToApplication")
            }

            override fun onImpression(p0: ImpressionData?) {
                Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onImpression: ${p0.toString()}")
            }

        })
        interstitialAd.loadAd(adRequest)
    }

    fun showRewarded(ctx: Context, openLink: () -> Unit) {
        rewardedAd = RewardedAd(ctx)
        rewardedAd.setAdUnitId(YANDEX_REWARDED_ID)
        rewardedAd.setRewardedAdEventListener(object : RewardedAdEventListener {
            override fun onAdLoaded() {
                rewardedAd.show()
                Timber.tag(YANDEX_REWARDED_TAG).d("onAdLoaded")
            }

            override fun onAdFailedToLoad(p0: AdRequestError) {
                Toast.makeText(ctx, "Повторите попытку позже", Toast.LENGTH_SHORT).show()
                Timber.tag(YANDEX_REWARDED_TAG).d("onAdFailedToLoad: ${p0.description}")
            }

            override fun onAdShown() {
                Timber.tag(YANDEX_REWARDED_TAG).d("onAdShown")
            }

            override fun onAdDismissed() {
                Timber.tag(YANDEX_REWARDED_TAG).d("onAdDismissed")
            }

            override fun onRewarded(p0: Reward) {
                openLink()
                Timber.tag(YANDEX_REWARDED_TAG).d("onRewarded: ${p0.type} - ${p0.amount}")
            }

            override fun onAdClicked() {
                Timber.tag(YANDEX_REWARDED_TAG).d("onAdClicked")
            }

            override fun onLeftApplication() {
                Timber.tag(YANDEX_REWARDED_TAG).d("onLeftApplication")
            }

            override fun onReturnedToApplication() {
                Timber.tag(YANDEX_REWARDED_TAG).d("onReturnedToApplication")
            }

            override fun onImpression(p0: ImpressionData?) {
                Timber.tag(YANDEX_REWARDED_TAG).d("onImpression: ${p0.toString()}")
            }

        })
        rewardedAd.loadAd(adRequest)
    }

}